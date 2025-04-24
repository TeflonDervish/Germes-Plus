document.querySelectorAll('.heart').forEach(button => {
    button.addEventListener('click', function() {
        this.closest('.product').remove();
    });
});

// Обработчики для кнопок увеличения количества
document.querySelectorAll('.increment').forEach(button => {
    button.addEventListener('click', function() {
        const input = this.parentElement.querySelector('.quantity');
        input.value = parseInt(input.value) + 1;
    });
});

// Обработчики для кнопок уменьшения количества
document.querySelectorAll('.decrement').forEach(button => {
    button.addEventListener('click', function() {
        const input = this.parentElement.querySelector('.quantity');
        if (parseInt(input.value) > 1) {
            input.value = parseInt(input.value) - 1;
        }
    });
});



document.addEventListener('DOMContentLoaded', function() {
    // Переключение между доставкой и самовывозом
    const deliveryRadio = document.getElementById('delivery');
    const pickupRadio = document.getElementById('pickup');
    const deliveryForm = document.getElementById('delivery-form');
    const pickupForm = document.getElementById('pickup-form');
    
    deliveryRadio.addEventListener('change', function() {
        if (this.checked) {
            deliveryForm.style.display = 'block';
            pickupForm.style.display = 'none';
        }
    });
    
    pickupRadio.addEventListener('change', function() {
        if (this.checked) {
            deliveryForm.style.display = 'none';
            pickupForm.style.display = 'block';
        }
    });
    
    // Показать/скрыть детали подъема
    const liftingCheckbox = document.getElementById('lifting');
    const liftingDetails = document.getElementById('lifting-details');
    
    liftingCheckbox.addEventListener('change', function() {
        liftingDetails.style.display = this.checked ? 'block' : 'none';
    });


    const pickupPointSelect = document.getElementById('pickup-point');
    const pickupInfo = document.getElementById('pickup-info');
    const pickupAddress = document.getElementById('pickup-address');
    const pickupPhone = document.getElementById('pickup-phone');
    const pickupMap = document.getElementById('pickup-map');
    
    let map; // Переменная для хранения объекта карты
    
    pickupPointSelect.addEventListener('change', function() {
        const selectedOption = this.options[this.selectedIndex];
        
        if (selectedOption.value) {
            // Показываем информацию
            pickupInfo.style.display = 'block';
            pickupAddress.textContent = selectedOption.dataset.address;
            pickupPhone.textContent = selectedOption.dataset.phone;
            
            // Инициализируем карту
            initMap(selectedOption.dataset.coords);
        } else {
            pickupInfo.style.display = 'none';
        }
    });
    
    // Функция инициализации карты (Яндекс.Карты)
    function initMap(coords) {
        // Удаляем предыдущую карту если есть
        if (map) {
            pickupMap.innerHTML = ''; // Очищаем контейнер
        }
        
        // Разделяем координаты
        const [lat, lon] = coords.split(',');
        

        ymaps.ready(function() {
            map = new ymaps.Map("pickup-map", {
                center: [lat, lon],
                zoom: 15
            });
            
            // Добавляем метку
            const placemark = new ymaps.Placemark([lat, lon], {
                hintContent: pickupPointSelect.options[pickupPointSelect.selectedIndex].text,
                balloonContent: pickupAddress.textContent
            });
            
            map.geoObjects.add(placemark);
        });
    }
    

});

