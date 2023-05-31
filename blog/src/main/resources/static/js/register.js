 function validateForm() {
    var password1 = document.getElementById("password1").value;
    var password2 = document.getElementById("password2").value;

    if (password1 !== password2) {
      alert("两次输入的密码不一致，请重新输入。");
      return false; // 阻止表单提交
    }

    return true; // 允许表单提交
  }