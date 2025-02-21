// Elementos da página
const gerarLinkButton = document.getElementById("gerarLink");
const linkChamada = document.getElementById("linkChamada");
const urlChamada = document.getElementById("urlChamada");
const finalizarChamadaButton = document.getElementById("finalizarChamada");
const pagamentoSection = document.getElementById("pagamentoSection");
const planoSaudeInput = document.getElementById("planoSaude");
const pagamentoInfo = document.getElementById("pagamentoInfo");
const avaliacaoSection = document.getElementById("avaliacaoSection");
const salvarAvaliacaoButton = document.getElementById("salvarAvaliacao");
const mensagemAvaliacao = document.getElementById("mensagemAvaliacao");
const estrelas = document.querySelectorAll(".star-rating .fa-star");

let avaliacaoSelecionada = 0; // Armazena a nota da avaliação

// Simulação dos dados do paciente
const paciente = {
    nome: "João Silva",
    idade: 30,
    planoSaude: "Unimed",
    medico: "Dr. Ricardo",
    especialidade: "Cardiologia"
};

// Preencher plano de saúde
planoSaudeInput.value = paciente.planoSaude;
planoSaudeInput.disabled = true;

// Gerar link para chamada
gerarLinkButton.addEventListener("click", () => {
    const link = `https://meet.google.com/${Math.random().toString(36).substr(2, 10)}`;
    
    urlChamada.href = link;
    urlChamada.textContent = link;
    linkChamada.style.display = "block";
    finalizarChamadaButton.style.display = "block";
});

// Finalizar chamada e exibir pagamento e avaliação
finalizarChamadaButton.addEventListener("click", () => { 
    alert("Chamada finalizada.");
    linkChamada.style.display = "none";
    finalizarChamadaButton.style.display = "none";

    pagamentoSection.classList.remove("hidden");
    avaliacaoSection.classList.remove("hidden");

    if (paciente.planoSaude) {
        pagamentoInfo.innerHTML = "<strong>Consulta coberta pelo plano.</strong>";
    } else {
        pagamentoInfo.innerHTML = `<strong>Valor da consulta: R$ 200,00</strong>`;
    }
});

// Adicionar funcionalidade de avaliação com estrelas
estrelas.forEach((estrela, index) => {
    estrela.addEventListener("click", () => {
        avaliacaoSelecionada = index + 1; // Define a avaliação escolhida (1 a 5)

        // Resetando todas as estrelas
        estrelas.forEach((el, i) => {
            if (i < avaliacaoSelecionada) {
                el.classList.add("selected"); // Preenche estrelas até a escolhida
            } else {
                el.classList.remove("selected");
            }
        });
    });
});

// Salvar avaliação
salvarAvaliacaoButton.addEventListener("click", () => {
    if (avaliacaoSelecionada > 0) {
        mensagemAvaliacao.textContent = `Avaliação enviada com sucesso! Nota: ${avaliacaoSelecionada} estrelas.`;
    } else {
        mensagemAvaliacao.textContent = "Por favor, selecione uma nota antes de enviar.";
    }
});
