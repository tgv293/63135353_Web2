$(document).ready(function () {
  // Khi trường input, textarea hoặc .input--style-5 được focus
  $("input, textarea, .input--style-5").on("focus", function () {
    $(this).addClass("input-focus");
  });

  // Khi trường input, textarea hoặc .input--style-5 bị blur
  $("input, textarea, .input--style-5").on("blur", function () {
    $(this).removeClass("input-focus");
  });

  // Ban đầu, vô hiệu hóa nút submit
  $(".btn_signup").prop("disabled", true);

  // Đặt trường body và reminderTime là hợp lệ ban đầu
  var initialValidFields = $(
    'input[name="body"], input[name="reminderTime"], input[name="reminder"]'
  );
  initialValidFields.addClass("valid");
  initialValidFields.trigger("input");
});

var VALID_EMAIL_ADDRESS_REGEX = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,6}$/i;
var VALID_PASSWORD_REGEX =
  /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;

var MAX_HEADER_LENGTH = 500;
var MAX_BODY_LENGTH = 5000;

var lengthErrorMessages = {
  header: "Tiêu đề không được quá " + MAX_HEADER_LENGTH + " ký tự",
  body: "Nội dung không được quá " + MAX_BODY_LENGTH + " ký tự",
};

var errorMessages = {
  lastName: "Họ không được trống",
  firstName: "Tên không được trống",
  email: "Email không được trống",
  address: "Địa chỉ không được trống",
  decryptedPassword: "Mật khẩu không được trống",
  repassword: "Xác nhận không được trống",
  username: "Email không được trống",
  password: "Mật khẩu không được trống",
  header: "Tiêu đề không được trống",
};

var formatErrorMessages = {
  email: "Email không hợp lệ",
  decryptedPassword: "Ít nhất 8 ký tự, gồm chữ hoa, thường, số, ký tự đặc biệt",
  repassword: "Chưa trùng khớp",
  // Thêm các thông báo lỗi định dạng khác ở đây
};

// Kiểm tra tất cả các trường có hợp lệ không
function checkAllFieldsValid() {
  var allValid = true;
  $(".input--style-4, .input--style-5").each(function () {
    var input = $(this);
    if (
      input.attr("name") !== "body" &&
      input.attr("name") !== "reminder" &&
      input.attr("name") !== "reminderTime" &&
      (!input.val() || input.hasClass("invalid"))
    ) {
      allValid = false;
      return false; // Thoát khỏi vòng lặp .each
    }
  });
  return allValid;
}

$(".input--style-4, .input--style-5")
  .not(".select")
  .on("input", function () {
    var input = $(this);
    var inputValue = input.val();

    if (
      inputValue ||
      input.attr("name") === "reminder" ||
      input.attr("name") === "body" ||
      input.attr("name") === "reminderTime"
    ) {
      if (
        input.attr("name") === "header" &&
        inputValue.length > MAX_HEADER_LENGTH
      ) {
        input.removeClass("valid").addClass("invalid");
        $("#" + input.attr("name") + "-error")
          .addClass("show")
          .text(lengthErrorMessages[input.attr("name")]);
      } else if (
        input.attr("name") === "body" &&
        inputValue.length > MAX_BODY_LENGTH
      ) {
        input.removeClass("valid").addClass("invalid");
        $("#" + input.attr("name") + "-error")
          .addClass("show")
          .text(lengthErrorMessages[input.attr("name")]);
      } else if (
        input.attr("name") === "email" &&
        !VALID_EMAIL_ADDRESS_REGEX.test(inputValue)
      ) {
        input.removeClass("valid").addClass("invalid");
        $("#" + input.attr("name") + "-error")
          .addClass("show")
          .text(formatErrorMessages[input.attr("name")]);
      } else if (
        input.attr("name") === "decryptedPassword" &&
        !VALID_PASSWORD_REGEX.test(inputValue)
      ) {
        input.removeClass("valid").addClass("invalid");
        $("#" + input.attr("name") + "-error")
          .addClass("show")
          .text(formatErrorMessages[input.attr("name")]);
      } else if (
        input.attr("name") === "repassword" &&
        inputValue !== $('input[name="decryptedPassword"]').val()
      ) {
        input.removeClass("valid").addClass("invalid");
        $("#" + input.attr("name") + "-error")
          .addClass("show")
          .text(formatErrorMessages[input.attr("name")]);
      } else {
        input.removeClass("invalid").addClass("valid");
        $("#" + input.attr("name") + "-error")
          .removeClass("show")
          .text("");
      }
    } else if (
      input.attr("name") !== "body" &&
      input.attr("name") !== "reminderTime" &&
      input.attr("name") !== "reminder"
    ) {
      input.removeClass("valid").addClass("invalid");
      $("#" + input.attr("name") + "-error")
        .addClass("show")
        .text(errorMessages[input.attr("name")]);
    }

    // Sau khi kiểm tra hợp lệ, kiểm tra tất cả các trường có hợp lệ và bật/tắt nút submit tương ứng
    if (checkAllFieldsValid()) {
      $(".btn_signup, .btn_save").prop("disabled", false);
    } else {
      $(".btn_signup, .btn_save").prop("disabled", true);
    }
  });

$(document).ready(function () {
  $(
    'input[name="body"], input[name="reminderTime"], input[name="reminder"]'
  ).trigger("input");
});
