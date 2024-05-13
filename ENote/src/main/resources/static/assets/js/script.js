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

$(document).ready(function(){
    // Get the current state from localStorage
    var currentState = localStorage.getItem('activeLink');

    // If there is a current state, remove 'active' from all links and add it to the current link
    if (currentState) {
        $('.note-link').removeClass('active');
        $('#' + currentState).addClass('active');
    }

    $('.note-link').click(function(event){
        // Prevent the default action (the page reload)
        event.preventDefault();

        // When a link is clicked, remove 'active' from all links and add it to the clicked link
        $('.note-link').removeClass('active');
        $(this).addClass('active');

        // Save the id of the clicked link to localStorage
        localStorage.setItem('activeLink', $(this).attr('id'));

        // Navigate to the link's href manually
        window.location.href = $(this).attr('href');
    });
});
