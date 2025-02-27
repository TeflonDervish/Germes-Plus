
const filters = document.querySelectorAll('.filters');
filters.forEach(filter => {
    filter.addEventListener('click', function (event) {
        event.stopPropagation(); 
        filters.forEach(otherFilter => {
            if (otherFilter !== this) {
                otherFilter.classList.remove('active');
            }
        });
        this.classList.toggle('active');
    });
});

document.addEventListener('click', function () {
    filters.forEach(filter => {
        filter.classList.remove('active');
    });
});

// Останавливаем всплытие события для инпутов
const inputs = document.querySelectorAll('.dropdown-content');
inputs.forEach(input => {
    input.addEventListener('click', function (event) {
        event.stopPropagation();
    });
});





document.querySelector('#toggle-filters').addEventListener('click', function() {
    const filtersContent = document.querySelector('.filt_all');
    const arrow = document.querySelector('.arrow');
  
    // Переключаем видимость контента
    filtersContent.classList.toggle('open');
  
    // Вращаем стрелку (опционально)
    if (arrow) {
      arrow.classList.toggle('rotate');
    }
  });
  
