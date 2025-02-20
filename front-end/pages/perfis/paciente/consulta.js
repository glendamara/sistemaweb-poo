const modal = document.getElementById("modalAvaliacao");
const span = document.getElementsByClassName("close")[0];

function mostrarModal() {
    modal.style.display = "block"; //mostrar o modal
}

span.onclick = function() {
    modal.style.display = "none"; //fecha
}

window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none"; //fechar o modal ao clicar fora
    }
}

document.getElementById("salvarAvaliacaoModal").addEventListener("click", function() {
    // Lógica para salvar a avaliação
    const estrelasSelecionadas = document.querySelectorAll(".star-rating-modal .fa-star.selected");
    const avaliacao = estrelasSelecionadas.length;
    // Salvar a avaliação no localStorage ou enviar para a API
    // Fechar o modal após salvar
    modal.style.display = "none";
});

// Exibir o modal quando a chamada no Meet terminar
// chamar a função mostrarModal() quando a chamada no Meet encerrar

//selecionar estrelas na avaliação
const estrelas = document.querySelectorAll('.star-rating .fa-star');
estrelas.forEach(star => {
    star.addEventListener('click', () => {
        const value = star.getAttribute('data-value');
        estrelas.forEach(s => {
            s.classList.remove('selected');
            if (s.getAttribute('data-value') <= value) {
                s.classList.add('selected');
            }
        });
    });
});

const gerarLinkButton = document.getElementById("gerarLink");
const linkChamada = document.getElementById("linkChamada");
const urlChamada = document.getElementById("urlChamada");
const finalizarChamadaButton = document.getElementById("finalizarChamada");

gerarLinkButton.addEventListener("click", () => {
    const link = `https://meet.google.com/${Math.random().toString(36).substr(2, 10)}`; urlChamada.textContent = link;
     linkChamada.style.display = "block"; finalizarChamadaButton.style.display = "block"; });
    finalizarChamadaButton.addEventListener("click", () => { alert("Chamada finalizada."); //adicionar lógica adicional aqui, se precisar etc
});