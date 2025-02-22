
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
const inputs = document.querySelectorAll('.dropdown-content-price input');
inputs.forEach(input => {
    input.addEventListener('click', function (event) {
        event.stopPropagation();
    });
});

