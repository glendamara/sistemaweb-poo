document.getElementById("loginForm").addEventListener("submit", function (event) {
    event.preventDefault(); // Impede o envio tradicional do formulário

    // Coleta os dados do formulário
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

// Adicionando funcionalidade para mostrar/esconder a senha ao clicar no ícone de olho
document.getElementById("toggle-password").addEventListener("click", function() {
    const senhaInput = document.getElementById("senha");
    const icon = document.querySelector("#toggle-password i");

    // Alterna entre tipo de input password/text
    if (senhaInput.type === "password") {
        senhaInput.type = "text";
        icon.classList.remove("fa-eye"); // Remove o ícone de olho fechado
        icon.classList.add("fa-eye-slash"); // Adiciona o ícone de olho aberto
    } else {
        senhaInput.type = "password";
        icon.classList.remove("fa-eye-slash");
        icon.classList.add("fa-eye");
    }
});

// Adicionando animação no ícone de olho
const eyeIcon = document.querySelector("#toggle-password i");

// Aplique a transição de forma correta no CSS
eyeIcon.style.transition = "transform 0.3s ease"; // Certifique-se de que a transição está definida

// Eventos de mouseover e mouseout para a animação do zoom
eyeIcon.addEventListener("mouseover", function() {
    eyeIcon.style.transform = "scale(1.3)"; // Aumenta o tamanho do ícone quando o mouse passa por cima
});

eyeIcon.addEventListener("mouseout", function() {
    eyeIcon.style.transform = "scale(1)"; // Restaura o tamanho original ao tirar o mouse
});
