
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






let isFiltersOpen = false; 
let lastClickedButton = null; 

document.querySelector('#toggle-filters').addEventListener('click', function (event) {
  const filtersContent = document.querySelector('.filt_all');
  const arrow = document.querySelector('.arrow');
  if (lastClickedButton === event.target) {
    filtersContent.classList.toggle('open');

    if (arrow) {
      if (isFiltersOpen) {
        arrow.classList.add('rotate'); // Вращаем стрелку, если контент был открыт и теперь закрывается
      } else {
        arrow.classList.remove('rotate'); // Возвращаем стрелку в исходное положение, если контент открывается
      }
    }

    isFiltersOpen = !isFiltersOpen;
  } else {
    // Если это первый клик или клик на другую кнопку, открываем контент без вращения стрелки
    filtersContent.classList.add('open');
    isFiltersOpen = true;
  }
  lastClickedButton = event.target;
});