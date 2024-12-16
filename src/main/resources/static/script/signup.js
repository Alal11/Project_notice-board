document.addEventListener('DOMContentLoaded', function (){
    // DOM 요소 가져오기
    const pw = document.getElementById('userPw');
    const pwCheck = document.getElementById('userPwCheck');
    const message = document.getElementById('message');
    const submitBtn = document.getElementById('submitBtn');

    // 비밀번호 확인 함수
    function checkPassword(){
        if(pw.value === pwCheck.value){
            message.textContent = '';
            submitBtn.disabled = false;
        }else{
            message.textContent = '비밀번호가 일치하지 않습니다.';
            submitBtn.disabled = true;
        }
    }

    // 이벤트 리스너 등록
    pw.addEventListener('input', checkPassword);
    pwCheck.addEventListener('input', checkPassword);
});


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

    // 비밀번호 확인 기능
    const userPw = document.getElementById('userPw');
    const userPwCheck = document.getElementById('userPwCheck');
    const message = document.getElementById('message');
    const submitBtn = document.getElementById('submitBtn');

    function validatePassword() {
        if (userPwCheck.value === '') {
            message.textContent = '';
            submitBtn.disabled = true;
        } else if (userPw.value !== userPwCheck.value) {
            message.textContent = '비밀번호가 일치하지 않습니다.';
            submitBtn.disabled = true;
        } else {
            message.textContent = '';
            submitBtn.disabled = false;
        }
    }

    userPw.addEventListener('input', validatePassword);
    userPwCheck.addEventListener('input', validatePassword);
});