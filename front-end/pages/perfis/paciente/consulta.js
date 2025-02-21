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
const copiarQrButton = document.getElementById("copiarQr");

let avaliacaoSelecionada = 0; 

const planoSaudeSalvo = sessionStorage.getItem("planoSaude");

if (planoSaudeSalvo) {
    planoSaudeInput.value = planoSaudeSalvo;
    planoSaudeInput.disabled = true;
}

gerarLinkButton.addEventListener("click", () => {
    const link = `https://meet.google.com/${Math.random().toString(36).substr(2, 10)}`;
    
    urlChamada.href = link;
    urlChamada.textContent = link;
    linkChamada.style.display = "block";
    finalizarChamadaButton.style.display = "block";
});

// acaba chamada e exibe pagamento e avaliação
finalizarChamadaButton.addEventListener("click", () => { 
    alert("Chamada finalizada.");
    linkChamada.style.display = "none";
    finalizarChamadaButton.style.display = "none";

    pagamentoSection.classList.remove("hidden");
    avaliacaoSection.classList.remove("hidden");

    if (planoSaudeSalvo && planoSaudeSalvo !== "nenhum") {
        pagamentoInfo.innerHTML = "<strong>Consulta coberta pelo plano.</strong>";
    } else {
        pagamentoInfo.innerHTML = `<strong>Valor da consulta: R$ 150,00</strong>`;
    }
});

//copiar código do pix
copiarQrButton.addEventListener("click", () => {
    navigator.clipboard.writeText("codigo-do-qr-code-aqui").then(() => {
        alert("Código QR copiado!");
    });
});
