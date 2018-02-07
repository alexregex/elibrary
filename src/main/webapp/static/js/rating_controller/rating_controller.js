'use strict';

App.controller("BookRatingDtoController", ['$scope', 'BookRatingDto', function($scope, BookRatingDto){
    var self = this;
    self.bookRatingDto = new BookRatingDto;
    self.newBookRatingDto = new BookRatingDto;

    self.bookId = 0;
    self.userLogin='';
    self.rating1 = 0;
    self.isReadonly = false;

    self.getBookRating = function (book_id, user_login) {
        var bookRatingDto1 = BookRatingDto.get({book_id : book_id, user_login : user_login}, function () {
                self.bookId = book_id;
                self.userLogin = user_login;

                self.rating1 = Math.round(bookRatingDto1.rating);
                self.bookRatingDto = bookRatingDto1;

                console.log("self.bookRatingDto.votes: " + self.bookRatingDto.votes);
                console.log("self.bookRatingDto.rating: " + self.bookRatingDto.rating);
                self.checkIsVoted();
            }
        );
    };

    self.checkIsVoted = function () {
            if (self.bookRatingDto.voted) {
                self.isReadonly = true;
                document.getElementById("submit").disabled = true;
            }
    };

    self.buttonLabel = function () {
            if (self.bookRatingDto.voted) {
                return 'You voted!';
            } else {
                return 'Vote!';
            }
    };

    self.saveBookRating = function () {
        console.log('self.saveBookRating calling');
        self.newBookRatingDto.rating = self.rating1;
        self.newBookRatingDto.bookId = self.bookId;
        self.newBookRatingDto.userLogin = self.userLogin;
        self.newBookRatingDto.$save();
        self.getBookRating(self.bookId, self.userLogin);
    };

    self.submit = function () {
        self.saveBookRating();
        self.getBookRating(self.bookId, self.userLogin);
    };
}]).directive('starRating',
    function () {
        return {
            restrict: 'EA',
            template:
            '<ul class="star-rating" ng-class="{readonly: readonly}">' +
            '  <li ng-repeat="star in stars" class="star" ng-class="{filled: star.filled}" ng-click="toggle($index)">' +
            '    <i class="fa fa-star"></i>' + // or &#9733
            '  </li>' +
            '</ul>',
            scope: {
                ratingValue: '=ngModel',
                max: '=?', // optional (default is 5)
                readonly: '=?'
            },
            link: function(scope, element, attributes) {
                if (scope.max == undefined) {
                    scope.max = 5;
                }
                function updateStars() {
                    scope.stars = [];
                    for (var i = 0; i < scope.max; i++) {
                        scope.stars.push({
                            filled: i < scope.ratingValue
                        });
                    }
                };
                scope.toggle = function(index) {
                    if (scope.readonly == undefined || scope.readonly === false){
                        scope.ratingValue = index + 1;
                    }
                };
                scope.$watch('ratingValue', function(oldValue, newValue) {
                    if (newValue || newValue === 0) {
                        updateStars();
                    }
                });
            }
        };
    }
    );