document.getElementById("loginForm").addEventListener("submit", function (event) {
    event.preventDefault();

    const nome = document.getElementById("nome").value;
    const senha = document.getElementById("senha").value;

    // Configura o corpo da requisição (payload)
    const loginData = {
        nome: nome,
        senha: senha
    };

    // Fazendo a requisição HTTP para o back-end usando fetch
    fetch("http://localhost:8080/api/pacientes/login", {
        method: "POST", // Método de envio
        headers: {
            "Content-Type": "application/json" // Tipo de conteúdo
        },
        body: JSON.stringify(loginData) // Dados convertidos para JSON
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Falha no login!');
        }
        return response.json();
    })
    .then(data => {
        // Aqui você pode tratar a resposta do back-end
        if (data.sucesso) {
            alert("Login realizado com sucesso!");
            // Redireciona o usuário para a página de agendamentos ou dashboard
            window.location.href = "/pagina-do-paciente"; 
        } else {
            alert("Credenciais inválidas! Tente novamente.");
        }
    })
    .catch(error => {
        console.error('Erro ao fazer login:', error);
        alert('Erro ao tentar fazer login. Tente novamente mais tarde.');
    });
});

document.addEventListener("DOMContentLoaded", function () {
    const senhaInput = document.getElementById("senha");
    const togglePasswordButton = document.getElementById("toggle-password");
    const icon = togglePasswordButton.querySelector("i");

    icon.classList.remove("fa-eye");  
    icon.classList.add("fa-eye-slash");  

    togglePasswordButton.addEventListener("click", function () {
        if (senhaInput.type === "password") {
            senhaInput.type = "text";
            icon.classList.replace("fa-eye-slash", "fa-eye");  // Mostra olho aberto
        } else {
            senhaInput.type = "password"; 
            icon.classList.replace("fa-eye", "fa-eye-slash");  // Mostra olho cortado
        }
    });

    togglePasswordButton.addEventListener("mouseover", function () {
        icon.style.transform = "scale(1.3)";
    });

    togglePasswordButton.addEventListener("mouseout", function () {
        icon.style.transform = "scale(1)";
    });
});
