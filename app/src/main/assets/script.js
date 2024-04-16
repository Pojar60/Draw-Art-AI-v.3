document.addEventListener('DOMContentLoaded', function () {
    let svg = document.querySelector('svg');
    let isDragging = false;
    let startX, startY;

    svg.addEventListener('mousedown', function (e) {
        isDragging = true;
        svg.style.cursor = 'grabbing';
        startX = e.clientX - svg.offsetLeft;
        startY = e.clientY - svg.offsetTop;
        e.preventDefault();
    });

    svg.addEventListener('mousemove', function (e) {
        if (isDragging) {
            svg.style.left = (e.clientX - startX) + 'px';
            svg.style.top = (e.clientY - startY) + 'px';
        }
    });

    svg.addEventListener('mouseup', function () {
        isDragging = false;
        svg.style.cursor = 'grab';
    });

    svg.addEventListener('mouseleave', function () {
        isDragging = false;
        svg.style.cursor = 'grab';
    });
});
document.querySelectorAll('.color-button').forEach(button => {
    button.addEventListener('click', function() {
        // Удалить класс 'active' со всех кнопок
        document.querySelectorAll('.color-button').forEach(b => {
            b.classList.remove('active');
        });
        // Добавить класс 'active' только к нажатой кнопке
        this.classList.add('active');
    });
});