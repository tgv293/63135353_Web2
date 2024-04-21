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
    $('.input.select').select2({
		
	});
});
var inputs = document.querySelectorAll('.signup .container .form label .input');
inputs.forEach(function(input) {
    input.addEventListener('input', function() {
        if (this.value) {
            this.classList.add('has-input');
        } else {
            this.classList.remove('has-input');
        }
    });
});