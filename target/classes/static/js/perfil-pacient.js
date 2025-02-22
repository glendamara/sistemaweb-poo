document.addEventListener("DOMContentLoaded", async function () {
    // Simulação de dados de usuário
    const mockUserData = {
        nome: "Ana Souza",
        idade: 28,
        planoSaude: "Unimed"
    };

    // DOM
    const nomeInput = document.getElementById("nome");
    const idadeInput = document.getElementById("idade");
    const planoSaudeSelect = document.getElementById("planoSaude");
    const userName = document.getElementById("user-name");

    const editarBtn = document.getElementById("editarBtn");
    const salvarBtn = document.getElementById("salvarBtn");
    const agendarBtn = document.getElementById("agendarConsulta");
    const cancelarBtn = document.getElementById("cancelarAgendamento");
    const botaoProximaPagina = document.getElementById("proximapag");

    // Simula requisição da API
    async function getUserData() {
        try {
            return new Promise((resolve) => {
                setTimeout(() => {
                    const savedUser = JSON.parse(localStorage.getItem("userData")) || mockUserData;
                    resolve(savedUser);
                }, 1000);
            });
        } catch (error) {
            console.error("Erro ao buscar dados do usuário:", error);
            return mockUserData; 
        }
    }

    // Preenche os inputs com os dados do usuário
    async function loadUserData() {
        const userData = await getUserData();
        nomeInput.value = userData.nome;
        idadeInput.value = userData.idade;
        planoSaudeSelect.value = userData.planoSaude || "nenhum";
        userName.textContent = userData.nome;
    }

    // Ativar edição dos campos
    function enableEditing() {
        nomeInput.disabled = false;
        idadeInput.disabled = false;
        planoSaudeSelect.disabled = false;
        editarBtn.style.display = "none";
        salvarBtn.style.display = "inline-block";
    }

    // Salvar dados editados
    function saveUserData() {
        const updatedUser = {
            nome: nomeInput.value,
            idade: idadeInput.value,
            planoSaude: planoSaudeSelect.value
        };
        localStorage.setItem("userData", JSON.stringify(updatedUser));
        userName.textContent = updatedUser.nome;
        nomeInput.disabled = true;
        idadeInput.disabled = true;
        planoSaudeSelect.disabled = true;
        editarBtn.style.display = "inline-block";
        salvarBtn.style.display = "none";
    }

    // Agendar consulta
    function agendarConsulta() {
        const medicoSelecionado = document.getElementById("medicoSelecionado").value;
        const dataConsulta = document.getElementById("dataConsulta").value;

        if (medicoSelecionado && dataConsulta) {
            alert("Consulta agendada!");
        } else {
            alert("Por favor, preencha todos os campos antes de agendar.");
        }
    }

    // Cancelar consulta
    function cancelarConsulta() {
        const consultaSelecionada = document.getElementById("cancelarConsulta").value;
        if (consultaSelecionada) {
            alert("Consulta cancelada!");
        } else {
            alert("Por favor, selecione uma consulta para cancelar.");
        }
    }

    // Redirecionamento para a próxima página, salvando plano de saúde no sessionStorage
    botaoProximaPagina.addEventListener("click", function () {
        sessionStorage.setItem("planoSaude", planoSaudeSelect.value);
        window.location.href = "consulta-p.html";
    });

    // Eventos
    editarBtn.addEventListener("click", enableEditing);
    salvarBtn.addEventListener("click", saveUserData);
    agendarBtn.addEventListener("click", agendarConsulta);
    cancelarBtn.addEventListener("click", cancelarConsulta);

    // Carrega os dados do usuário ao iniciar
    loadUserData();
});