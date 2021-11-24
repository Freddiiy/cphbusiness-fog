const sendButton = document.getElementById('sendButton');

const form = document.querySelector('.needs-validation');

sendButton.addEventListener('click', (event) => {
    let password1 = document.getElementById('floatingPassword').value;
    let password2 = document.getElementById('floatingPasswordRepeat').value;
    console.log(password1 + " " + password2);
    if(!form.checkValidity()) {
        event.preventDefault();
        event.stopPropagation();
    }
    form.classList.add('was-validated');
})

