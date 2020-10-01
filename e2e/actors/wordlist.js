
module.exports = function() {
    return actor({
        formField: {
            password : 'password',
            username: 'username',
            passwordConfirmation: 'confirmPassword',
            SubmitButton: 'Submit'
        },
        pageUrl: {
            login: '/login',
            root: '/',
            register: '/register',
            private: '/private'
        },
        Data: {
            usernameUnique: `User-${Date.now()}`,
            password: 'pass1234'
        }

    });
}
