function createStars() {
    const starsContainer = document.createElement('div');
    starsContainer.className = 'stars';
    document.body.prepend(starsContainer);

    const starCount = 200;

    for (let i = 0; i < starCount; i++) {
        const star = document.createElement('div');
        star.className = 'star';

        const x = Math.random() * 100;
        const y = Math.random() * 100;
        const size = Math.random() * 2;
        const duration = 3 + Math.random() * 3;

        star.style.cssText = `
            left: ${x}%;
            top: ${y}%;
            width: ${size}px;
            height: ${size}px;
            animation: twinkle ${duration}s infinite;
        `;

        starsContainer.appendChild(star);
    }
}

document.addEventListener('DOMContentLoaded', function() {
    createStars();

    const title = document.querySelector('.title');
    title.style.opacity = '0';
    title.style.transform = 'translateY(-20px)';

    setTimeout(() => {
        title.style.transition = 'all 1.2s ease';
        title.style.opacity = '1';
        title.style.transform = 'translateY(0)';
    }, 300);
});