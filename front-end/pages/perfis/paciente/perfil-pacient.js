document.addEventListener("DOMContentLoaded", async function () {
    // Simulação de dados de usuário (antes da API)
    const mockUserData = {
        nome: "Ana Souza",
        idade: 28,
        planoSaude: "Unimed"
    };

    //DOM
    const nomeInput = document.getElementById("nome");
    const idadeInput = document.getElementById("idade");
    const planoSaudeInput = document.getElementById("planoSaude");
    const userName = document.getElementById("user-name");

    const editarBtn = document.getElementById("editarBtn");
    const salvarBtn = document.getElementById("salvarBtn");
    const agendarBtn = document.getElementById("agendarConsulta");
    const cancelarBtn = document.getElementById("cancelarAgendamento");

    // Simula requisição da API
    async function getUserData() {
        try {
            // Simulação de API - Substituir por um fetch real quando disponível
            return new Promise((resolve) => {
                setTimeout(() => {
                    const savedUser = JSON.parse(localStorage.getItem("userData")) || mockUserData;
                    resolve(savedUser);
                }, 1000);
            });
        } catch (error) {
            console.error("Erro ao buscar dados do usuário:", error);
            return mockUserData; // retorna dados padrão (em caso de erro
        }
    }

    // preenche os inputs com os dados do usuário
    async function loadUserData() {
        const userData = await getUserData();

        // exibe os dados nos inputs (pré-visualização)
        nomeInput.value = userData.nome;
        idadeInput.value = userData.idade;
        planoSaudeInput.value = userData.planoSaude;

        // atualiza o nome no cabeçalho
        userName.textContent = userData.nome;
    }

    // ativar os campos de edição
    function enableEditing() {
        nomeInput.disabled = false;
        idadeInput.disabled = false;
        planoSaudeInput.disabled = false;

        editarBtn.style.display = "none";
        salvarBtn.style.display = "inline-block";
    }

    //salvar os dados editados
    function saveUserData() {
        const updatedUser = {
            nome: nomeInput.value,
            idade: idadeInput.value,
            planoSaude: planoSaudeInput.value
        };

        // salva no localStorage
        localStorage.setItem("userData", JSON.stringify(updatedUser));

        // atualizar o nome na saudação
        userName.textContent = updatedUser.nome;

        // bloquear dnv os campos
        nomeInput.disabled = true;
        idadeInput.disabled = true;
        planoSaudeInput.disabled = true;

        editarBtn.style.display = "inline-block";
        salvarBtn.style.display = "none";
    }

    // agendar consulta
    function agendarConsulta() {
        const medicoSelecionado = document.getElementById("medicoSelecionado").value;
        const dataConsulta = document.getElementById("dataConsulta").value;

        if (medicoSelecionado && dataConsulta) {
            alert("Consulta agendada!");
        } else {
            alert("Por favor, preencha todos os campos antes de agendar.");
        }
    }

    // cancelar consulta
    function cancelarConsulta() {
        const consultaSelecionada = document.getElementById("cancelarConsulta").value;

        if (consultaSelecionada) {
            alert("Consulta cancelada!");
        } else {
            alert("Por favor, selecione uma consulta para cancelar.");
        }
    }

    editarBtn.addEventListener("click", enableEditing);
    salvarBtn.addEventListener("click", saveUserData);
    agendarBtn.addEventListener("click", agendarConsulta);
    cancelarBtn.addEventListener("click", cancelarConsulta);

    loadUserData();
});
