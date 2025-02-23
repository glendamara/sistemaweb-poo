document.addEventListener("DOMContentLoaded", () => {
    const forms = document.querySelectorAll("form");
    forms.forEach(form => {
        form.addEventListener("submit", (e) => {
            const inputs = form.querySelectorAll("input");
            inputs.forEach(input => {
                if (!input.value.trim()) {
                    e.preventDefault();
                    alert("Por favor, preencha todos os campos.");
                    input.focus();
                }
            });
        });
    });
});
