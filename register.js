
function validatePassword() {
    var password1 = document.getElementById('password1').value;
    var password2 = document.getElementById('password2').value;

    if (password1 !== password2) {
      alert('两次密码输入不一致！');
      return false;
    }

    return true;
}