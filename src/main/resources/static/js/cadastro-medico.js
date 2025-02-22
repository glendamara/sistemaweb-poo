document.addEventListener("DOMContentLoaded", function() {
    const senhaInput = document.getElementById("senha");
    const toggleIcon = document.getElementById("toggleSenha");

    toggleIcon.addEventListener("click", function() {
        if (senhaInput.type === "password") {
            senhaInput.type = "text"; 
            toggleIcon.classList.remove("fa-eye-slash");
            toggleIcon.classList.add("fa-eye");
        } else {
            senhaInput.type = "password";
            toggleIcon.classList.remove("fa-eye");
            toggleIcon.classList.add("fa-eye-slash");
        }
    });
});

function cadastrar() {
    const nome = document.getElementById('nome').value;
    const especialidade = document.getElementById('especialidade').value;
    const serviço = document.getElementById('serviço').value;
    const email = document.getElementById('email').value;
    const senha = document.getElementById('senha').value;

    if (!nome || !especialidade || !serviço || !email || !senha) {
        alert('Preencha todos os campos!');
        return;
    }

    alert('Cadastro realizado com sucesso!');
    //add código para enviar os dados para o back-end
}