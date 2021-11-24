const sendButton = document.getElementById('sendButton');

const form = document.querySelector('.needs-validation');

sendButton.addEventListener('click', (event) => {
    if(!form.checkValidity()) {
        event.preventDefault();
        event.stopPropagation();
    }
    form.classList.add('was-validated');
})

