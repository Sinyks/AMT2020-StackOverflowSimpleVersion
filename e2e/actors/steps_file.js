// in this file you can append custom step methods to 'I' object
const { Wordlist } = inject();

module.exports = function() {
  return actor({

    // Define custom steps here, use 'this' to access default methods of I.
    // It is recommended to place a general 'login' function here.
    login: function(username, password) {
      this.fillField(Wordlist.formField.username, username);
      this.fillField(Wordlist.formField.password, password);
      this.click(Wordlist.formField.SubmitButton);
    },

    register: function(username, password) {
      this.amOnPage(Wordlist.pageUrl.register);
      this.fillField(Wordlist.formField.username, username);
      this.fillField(Wordlist.formField.password, password);
      this.fillField(Wordlist.formField.passwordConfirmation, password);
      this.click(Wordlist.formField.SubmitButton);
    },
    registerRandomUser: function(){
      const username = Wordlist.Data.usernameUnique;
      const password = Wordlist.Data.password;
      this.register(username,password);
    }
    
  });
}
