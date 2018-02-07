'use strict';

App.factory('BookRatingDto', function ($resource) {
    return $resource('http://localhost:8080/rating/:book_id/:user_login');
});