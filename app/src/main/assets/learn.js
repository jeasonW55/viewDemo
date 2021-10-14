
var zhangsan = {
    first_name: "zhangsan",
    last_name: "wwww",
    age: 0
};

var name2 = {
    first_name: "wangjishun",
    last_name: "li dai",
    age: 15
};

function showName2(name) {

    name.first_name = "China Number one";
    window.control.showToast("name.first_name " + name.first_name);
};

function showName3() {
    showName2(zhangsan)
};

function showName4() {
    window.control.showToast(name2.first_name);
};