document.addEventListener("DOMContentLoaded", function () {
    const editarBtn = document.getElementById("editarBtn");
    const salvarBtn = document.getElementById("salvarBtn");
    const nomeInput = document.getElementById("nome");
    const idadeInput = document.getElementById("idade");
    const planoTexto = document.getElementById("planoSaude");

    // Ativar edição dos campos
    editarBtn.addEventListener("click", function () {
        nomeInput.disabled = false;
        idadeInput.disabled = false;
        planoTexto.disabled = false;
        editarBtn.style.display = "none";
        salvarBtn.style.display = "block";
    });

    // Salvar os dados editados (simulação)
    salvarBtn.addEventListener("click", function (e) {
        e.preventDefault(); // Previne o envio do form
        alert(`Dados salvos:\nNome: ${nomeInput.value}\nIdade: ${idadeInput.value}\nPlano de Saúde: ${planoTexto.value}`);
        nomeInput.disabled = true;
        idadeInput.disabled = true;
        planoTexto.disabled = true;
        salvarBtn.style.display = "none";
        editarBtn.style.display = "block";
    });

    // Agendar consulta
    const agendarBtn = document.getElementById("agendarConsulta");
    agendarBtn.addEventListener("click", function () {
        const medico = document.getElementById("medicoSelecionado").value;
        const data = document.getElementById("dataConsulta").value;

        if (medico && data) {
            alert(`Consulta agendada com sucesso para ${data}.`);
        } else {
            alert("Por favor, preencha os campos necessários para o agendamento.");
        }
    });

    // Cancelar consulta
    const cancelarBtn = document.getElementById("cancelarBtn");
    cancelarBtn.addEventListener("click", function () {
        const consulta = document.getElementById("consultas").value;

        if (consulta) {
            alert(`Consulta cancelada com sucesso.`);
        } else {
            alert("Escolha uma consulta para cancelar.");
        }
    });
});
