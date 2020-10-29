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

    register: function(username,email,password) {
      this.amOnPage(Wordlist.pageUrl.register);
      this.fillField(Wordlist.formField.username, username);
      this.fillField(Wordlist.formField.email, email);
      this.fillField(Wordlist.formField.password, password);
      this.fillField(Wordlist.formField.passwordConfirmation, password);
      this.click(Wordlist.formField.SubmitButton);
    },
    registerRandomUser: function(){
      const username = Wordlist.Data.usernameUnique;
      const password = Wordlist.Data.password;
      const email = Wordlist.Data.emailUnique
      this.register(username,email,password);
    },
    askQuestions : function(){
      I.fillField(Wordlist.formField.Qlabel,Wordlist.Data.questionLabel);
      I.fillField(Wordlist.formField.Qcontent,Wordlist.Data.questionContent);
      I.click(Wordlist.formField.SubmitButton);
    },
    
  });
}
