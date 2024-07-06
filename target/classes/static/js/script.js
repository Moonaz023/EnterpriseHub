// JavaScript for toggling sub-options and saving state
window.addEventListener('DOMContentLoaded', (event) => {
  const options = document.querySelectorAll('.option');

  options.forEach(option => {
    // Retrieve saved state
    const isActive = localStorage.getItem(option.textContent.trim());
    if (isActive === 'true') {
      option.classList.add('active');
    }

    option.addEventListener('click', () => {
      option.classList.toggle('active');
      // Save state
      localStorage.setItem(option.textContent.trim(), option.classList.contains('active'));
    });

    const subOptions = option.querySelector('.sub-options');
    subOptions.addEventListener('click', (event) => {
      event.stopPropagation();
    });
  });
});
