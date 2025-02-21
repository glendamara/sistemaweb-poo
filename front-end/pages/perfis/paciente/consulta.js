document.addEventListener("DOMContentLoaded", () => {
    // Elementos da chamada e pagamento
    const gerarLinkButton = document.getElementById("gerarLink");
    const linkChamada = document.getElementById("linkChamada");
    const urlChamada = document.getElementById("urlChamada");
    const finalizarChamadaButton = document.getElementById("finalizarChamada");
    const pagamentoSection = document.getElementById("pagamentoSection");
    const copiarPixButton = document.getElementById("copiarPix");
    const enviarPagamentoButton = document.getElementById("enviarPagamento");
    const avaliacaoSection = document.getElementById("avaliacaoSection");
    const pagamentoInfo = document.getElementById("pagamentoInfo");
    const planoSaudeSelect = document.getElementById("planoSaude");

    // Elementos da avaliação
    const stars = document.querySelectorAll('.star-rating .fa-star');
    const ratingFeedback = document.getElementById('rating-feedback');
    const selectedRating = document.getElementById('selected-rating');
    const userComment = document.getElementById('user-comment');
    const submitFeedback = document.getElementById('submit-feedback');
    const thankYouMessage = document.getElementById('thank-you-message');

    let currentRating = 0; // Armazena a classificação atual

    // Recuperar o plano de saúde salvo na sessão
    const planoSalvo = sessionStorage.getItem("planoSaude") || "Nenhum";

    // Preencher o select com a opção salva
    const option = document.createElement("option");
    option.textContent = planoSalvo;
    planoSaudeSelect.appendChild(option);

    // Gerar link de chamada
    gerarLinkButton.addEventListener("click", () => {
        const link = `https://meet.google.com/${Math.random().toString(36).substr(2, 10)}`;
        urlChamada.href = link;
        urlChamada.textContent = link;
        linkChamada.classList.remove("hidden");
        finalizarChamadaButton.classList.remove("hidden");
    });

    // Finalizar chamada
    finalizarChamadaButton.addEventListener("click", () => {
        alert("Chamada finalizada.");
        linkChamada.classList.add("hidden");
        finalizarChamadaButton.classList.add("hidden");

        pagamentoSection.classList.remove("hidden");

        if (planoSalvo !== "Nenhum") {
            pagamentoInfo.innerHTML = `<strong>Consulta coberta pelo plano de saúde.</strong>`;
        } else {
            pagamentoInfo.innerHTML = `<strong>Valor da consulta: R$ 150,00</strong>`;
        }
    });

    // Copiar código PIX
    copiarPixButton.addEventListener("click", () => {
        const codigoPix = document.getElementById("codigoPix").textContent;
        navigator.clipboard.writeText(codigoPix).then(() => {
            alert("Código PIX copiado!");
            enviarPagamentoButton.classList.remove("hidden");
        });
    });

    // Enviar pagamento
    enviarPagamentoButton.addEventListener("click", () => {
        alert("Pagamento enviado com sucesso!");
        avaliacaoSection.classList.remove("hidden");
    });

    // Interação com as estrelas de avaliação
    stars.forEach(star => {
        star.addEventListener('click', function () {
            const rating = parseInt(star.getAttribute('data-rating'));
            currentRating = rating;

            // Atualiza a aparência das estrelas
            stars.forEach((s, index) => {
                if (index < rating) {
                    s.classList.add('selected');
                } else {
                    s.classList.remove('selected');
                }
            });

            // Atualiza o texto de feedback com a classificação selecionada
            selectedRating.textContent = rating;
            ratingFeedback.classList.remove("hidden");

            // Mostra o textarea e o botão de envio
            userComment.classList.remove("hidden");
            submitFeedback.classList.remove("hidden");
        });
    });

    // Enviar feedback
    submitFeedback.addEventListener('click', function () {
        const comment = userComment.value.trim();

        if (comment) {
            // Aqui você pode enviar o feedback para o servidor (usando fetch, por exemplo)
            console.log('Classificação:', currentRating);
            console.log('Comentário:', comment);

            // Esconde elementos e mostra a mensagem de agradecimento
            ratingFeedback.classList.add("hidden");
            userComment.classList.add("hidden");
            submitFeedback.classList.add("hidden");
            thankYouMessage.classList.remove("hidden");
        } else {
            alert('Por favor, deixe um comentário antes de enviar.');
        }
    });
});