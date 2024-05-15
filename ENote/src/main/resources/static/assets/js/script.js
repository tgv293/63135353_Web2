$(document).ready(function() {
    $('.toggle-password').click(function() {
        var input = $(this).siblings('input');
        if(input.attr('type') == 'password') {
            input.attr('type', 'text');         
            $(this).removeClass('fa-eye-slash').addClass('fa-eye');
        } else {
            input.attr('type', 'password');
            $(this).removeClass('fa-eye').addClass('fa-eye-slash');            
        }
    });
});

$(document).ready(function() {
    $('.select').select2({
		
	});
});

$(document).ready(function() {
    var navbarHeight = $('.nav_bar').outerHeight(); // Thay '.navbar' bằng class hoặc id của navbar của bạn
    console.log(navbarHeight);
    $('.section_custom').css('padding-top', navbarHeight + 'px'); // Thay '.section' bằng class hoặc id của section của bạn
});

$(document).ready(function(){
    var url = window.location.pathname;
    var filename = url.substring(url.lastIndexOf('/')+1);
    var referrer = document.referrer;
    var hasQueryParams = window.location.search.length > 0;

    // If the referrer is not 'viewNotes' and the current page is 'viewNotes', or if the current page is 'viewNotes' without query parameters, reset everything
    if((filename == 'viewNotes' && !referrer.includes('viewNotes')) || (filename == 'viewNotes' && !hasQueryParams)) {
        $('.note-link').removeClass('active');
        $('#all-notes').addClass('active');
        sessionStorage.removeItem('activeLink');
    } else {
        var currentState = sessionStorage.getItem('activeLink');

        if (currentState) {
            $('.note-link').removeClass('active');
            $('#' + currentState).addClass('active');
        }
    }

    $('.note-link').click(function(event){
        event.preventDefault();
        $('.note-link').removeClass('active');
        $(this).addClass('active');
        sessionStorage.setItem('activeLink', $(this).attr('id'));
        window.location.href = $(this).attr('href');
    });
});

$(document).ready(function() {
    $('.single-note-item').click(function() {
        var noteId = $(this).data('note-id');
        window.location.href = '/' + noteId;
    });
});