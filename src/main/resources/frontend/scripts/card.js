const images = document.querySelectorAll('.gallery-images img');
const prevButton = document.querySelector('.prev-button');
const nextButton = document.querySelector('.next-button');
let currentIndex = 0;

function showImage(index) {
    // Скрываем все изображения
    images.forEach(img => img.classList.remove('active'));
    // Показываем текущее изображение
    images[index].classList.add('active');
}

prevButton.addEventListener('click', () => {
    currentIndex = (currentIndex - 1 + images.length) % images.length;
    showImage(currentIndex);
});

nextButton.addEventListener('click', () => {
    currentIndex = (currentIndex + 1) % images.length;
    showImage(currentIndex);
});

// Показываем первое изображение при загрузке страницы
showImage(currentIndex);





document.getElementById('heart').addEventListener('click', function() {
    this.classList.toggle('active'); // Переключаем класс при клике
});








// Находим все кнопки и контент вкладок
const tabButtons = document.querySelectorAll('.tab-button');
const tabPanes = document.querySelectorAll('.tab-pane');

// Добавляем обработчик событий для каждой кнопки
tabButtons.forEach(button => {
    button.addEventListener('click', () => {
        // Убираем активный класс у всех кнопок и контента
        tabButtons.forEach(btn => btn.classList.remove('active'));
        tabPanes.forEach(pane => pane.classList.remove('active'));

        // Добавляем активный класс к выбранной кнопке и соответствующему контенту
        button.classList.add('active');
        const tabId = button.getAttribute('data-tab');
        document.getElementById(tabId).classList.add('active');
    });
});





const stars = document.querySelectorAll('.rating .star');
const ratingInput = document.getElementById('rating');

stars.forEach(star => {
    star.addEventListener('click', function() {
        const value = parseInt(this.getAttribute('data-value'), 10);
        ratingInput.value = value;

        // Обновляем отображение звезд
        stars.forEach((s, index) => {
            if (index < value) {
                s.classList.add('active');
            } else {
                s.classList.remove('active');
            }
        });
    });
});