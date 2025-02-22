const roleSwitch = document.getElementById('roleSwitch');
const clientForm = document.getElementById('clientForm');
const partnerForm = document.getElementById('partnerForm');
const clientFooter = document.getElementById('clientFooter');
const partnerFooter = document.getElementById('partnerFooter');

roleSwitch.addEventListener('change', function() {
  if (this.checked) {
    clientForm.classList.add('hidden');
    partnerForm.classList.remove('hidden');
    clientFooter.classList.add('hidden');
    partnerFooter.classList.remove('hidden');
  } else {
    clientForm.classList.remove('hidden');
    partnerForm.classList.add('hidden');
    clientFooter.classList.remove('hidden');
    partnerFooter.classList.add('hidden');
  }
});