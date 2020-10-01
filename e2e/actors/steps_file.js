// in this file you can append custom step methods to 'I' object

module.exports = function() {
  return actor({

    // Define custom steps here, use 'this' to access default methods of I.
    // It is recommended to place a general 'login' function here.
    login: function(username, password) {
      this.fillField('username', username);
      this.fillField('password', password);
      this.click('Submit');
    },

    register: function(username, password) {
      this.fillField('username', username);
      this.fillField('password', password);
      this.fillField('confirmPassword', password);
      this.click('Submit');
    },
    registerRandomUser: function(){
      const username = `User-${Date.now()}`;
      const password = 'pass1234';
      this.register(username,password);
    }
    
  });
}
