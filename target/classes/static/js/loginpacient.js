document.addEventListener("DOMContentLoaded", function () {
    const senhaInput = document.getElementById("senha");
    const togglePasswordButton = document.getElementById("toggle-password");
    const icon = togglePasswordButton.querySelector("i");

    // Define o estado inicial: senha oculta (ícone "fa-eye-slash")
    icon.classList.remove("fa-eye");  
    icon.classList.add("fa-eye-slash");  

    // Adiciona o evento de clique para alternar visibilidade da senha
    togglePasswordButton.addEventListener("click", function () {
        if (senhaInput.type === "password") {
            senhaInput.type = "text";
            icon.classList.replace("fa-eye-slash", "fa-eye"); // Ícone olho aberto
        } else {
            senhaInput.type = "password"; 
            icon.classList.replace("fa-eye", "fa-eye-slash"); // Ícone olho cortado
        }
    });
});
