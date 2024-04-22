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