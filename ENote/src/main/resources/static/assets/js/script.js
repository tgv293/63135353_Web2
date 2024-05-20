$(document).ready(function () {
  // Xử lý hiển thị mật khẩu
  $(".toggle-password").click(function () {
    var input = $(this).siblings("input");
    if (input.attr("type") == "password") {
      input.attr("type", "text");
      $(this).removeClass("fa-eye-slash").addClass("fa-eye");
    } else {
      input.attr("type", "password");
      $(this).removeClass("fa-eye").addClass("fa-eye-slash");
    }
  });

  // Xử lý khi cuộn trang
  $(window).scroll(function () {
    if ($(this).scrollTop() > 0) {
      // Kiểm tra nếu người dùng đã cuộn xuống
      $(".navbar").addClass("scrolled"); // Thêm lớp 'scrolled' vào navbar
    } else {
      $(".navbar").removeClass("scrolled"); // Loại bỏ lớp 'scrolled' khỏi navbar
    }
  });

  // Select2
  $(".select").select2({});

  // Tính chiều cao của navbar để điều chỉnh chiều cao của nội dung
  var navbarHeight = $(".nav_bar").outerHeight(); // Thay '.navbar' bằng class hoặc id của navbar của bạn
  $(".section_custom").css("padding-top", navbarHeight + "px"); // Thay '.section' bằng class hoặc id của section của bạn

  // Xử lý filter
  var url = window.location.pathname;
  var filename = url.substring(url.lastIndexOf("/") + 1);
  var referrer = document.referrer;
  var hasQueryParams = window.location.search.length > 0;

  // Nếu trang hiện tại là 'viewNotes' và không có referrer là 'viewNotes', hoặc nếu trang hiện tại là 'viewNotes' mà không có query parameters, thì reset tất cả
  if (
    (filename == "viewNotes" && !referrer.includes("viewNotes")) ||
    (filename == "viewNotes" && !hasQueryParams)
  ) {
    $(".note-link").removeClass("active");
    $("#all-notes").addClass("active");
    sessionStorage.removeItem("activeLink");
  } else {
    var currentState = sessionStorage.getItem("activeLink");

    if (currentState) {
      $(".note-link").removeClass("active");
      $("#" + currentState).addClass("active");
    }
  }

  $(".note-link").click(function (event) {
    event.preventDefault();
    $(".note-link").removeClass("active");
    $(this).addClass("active");
    sessionStorage.setItem("activeLink", $(this).attr("id"));
    window.location.href = $(this).attr("href");
  });

  // Lấy các tham số URL hiện tại
  var urlParams = new URLSearchParams(window.location.search);

  // Thêm tham số 'from' cho viewNotes
  urlParams.set("from", "viewNotes");

  // Thêm các bộ lọc hiện tại vào liên kết archive trong viewNotes
  $(".unarchive-link, delete-unarchive-link").each(function () {
    var archiveUrl = new URL($(this).attr("href"), window.location.origin);
    archiveUrl.search = urlParams.toString();
    $(this).attr("href", archiveUrl.toString());
  });

  // Thêm tham số 'from' cho viewArchivedNotes
  urlParams.set("from", "viewArchivedNotes");

  // Cập nhật href cho liên kết archive trong viewArchivedNotes
  $(".archive-link, .delete-archive-link").attr("href", function (i, href) {
    var archiveUrl = new URL(href, window.location.origin);
    archiveUrl.search = urlParams.toString();
    return archiveUrl.toString();
  });

  // Xử lý nút xóa
  $(".delete-unarchive-link, .delete-archive-link, .delete").click(function (
    e
  ) {
    if (!confirm("Bạn có chắc muốn xóa ghi chú này không?")) {
      e.preventDefault();
    }
    e.stopPropagation(); // Ngăn chặn sự kiện lan truyền lên .single-note-item
  });

  // Xử lý nút xóa người dùng
  $(".delete-user").click(function (e) {
    if (!confirm("Bạn có chắc muốn xóa tài khoản này không?")) {
      e.preventDefault();
    }
    e.stopPropagation();
  });

  // Xử lý khi nhấp vào ghi chú
  $(".single-note-item").click(function () {
    var noteId = $(this).data("note-id");
    window.location.href = "/" + noteId;
  });

  // Gửi yêu cầu lấy thông tin người dùng hiện tại
  fetch("/current-user", {
    credentials: "include",
  })
    .then((response) => {
      const contentType = response.headers.get("content-type");
      if (contentType && contentType.includes("application/json")) {
        return response.json();
      } else {
        // Chưa đăng nhập, xóa thông tin người dùng từ localStorage
        localStorage.removeItem("user");
        throw new Error("Not logged in");
      }
    })
    .then((user) => {
      if (user) {
        // Người dùng đã đăng nhập, làm gì đó với đối tượng người dùng
        // Lưu thông tin người dùng vào localStorage
        localStorage.setItem(
          "user",
          JSON.stringify({
            lastName: user.lastName,
            firstName: user.firstName,
            email: user.email,
          })
        );
      } else {
        // Người dùng đã đăng xuất, giải phóng tài nguyên hoặc chuyển hướng đến trang đăng nhập
        // Xóa thông tin người dùng từ localStorage
        localStorage.removeItem("user");
      }
    })
    .catch((error) => {
      if (error.message !== "Not logged in") {
        console.error("Error:", error);
      }
    });

  document
    .querySelector(".btn.btn-light.custom-button.ms-1.me-1")
    .addEventListener("click", function () {
      localStorage.removeItem("user");
    });

  // Yêu cầu quyền thông báo ngay khi trang tải
  Notification.requestPermission();

  if (localStorage.getItem("user")) {
    // Hàm fetchReminders gọi API và hiển thị thông báo
    function fetchReminders() {
      fetch("/reminders")
        .then((response) => response.json())
        .then((notes) => {
          let now = new Date();
          for (let note of notes) {
            let reminderTime = new Date(note.reminderTime);
            // Chỉ hiển thị thông báo nếu thời gian nhắc nhở trùng với phút hiện tại
            if (
              reminderTime.getMinutes() === now.getMinutes() &&
              reminderTime.getHours() === now.getHours() &&
              Notification.permission === "granted"
            ) {
              new Notification(note.header, { body: note.body });
            }
          }
        });
    }

    // Tính thời gian chờ ban đầu để đảm bảo rằng setInterval bắt đầu vào đầu mỗi phút
    let now = new Date();
    let delay = (60 - now.getSeconds()) * 1000;

    setTimeout(function () {
      fetchReminders();
      setInterval(fetchReminders, 60000);
    }, delay);
  }
});

document.addEventListener("DOMContentLoaded", (event) => {
  let reminderInput = document.getElementById("reminder");
  if (reminderInput) {
    // Chỉ chạy mã này nếu reminderInput không null
    let now = new Date();
    let month = (now.getMonth() + 1).toString().padStart(2, "0");
    let day = now.getDate().toString().padStart(2, "0");
    let hour = now.getHours().toString().padStart(2, "0");
    let minute = now.getMinutes().toString().padStart(2, "0");
    let formattedDate =
      now.getFullYear() + "-" + month + "-" + day + "T" + hour + ":" + minute;

    reminderInput.min = formattedDate;
  }
});
