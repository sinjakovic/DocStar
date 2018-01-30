/**
 * Created by Brandon on 4/21/2017.
 */
angular.module('Docstar.Users.Documents')
    .controller('Docstar.Users.Documents.Details.Controller', ['$scope', '$resource', '$routeParams', '$uibModal', 'Session.Service',function ($scope, $resource, $routeParams, $modal, Session) {
        var Document = $resource('api/v1/user/documents/:dID', {});
        var Rating = $resource('/api/v1/user/documents/:dID/rating');

        $scope.doc = Document.get({dID: $routeParams.dID});
        $scope.rating = Rating.get({dID: $routeParams.dID});

        $scope.review = function () {
            var modal = $modal.open({
                    templateUrl: 'resources/views/users/documents/newRating.html',
                    controller: 'Docstar.Documents.NewRatingController',
                    resolve: {
                        doc: $scope.doc
                    }
                }
            );

            modal.result.then(function (newRating) {
                newRating.user = Session.user();
                delete newRating.user.authorities;
                newRating.docNumber = $routeParams.dID;
                Rating.save({dID: $routeParams.dID},
                    newRating,
                    function (savedRating) {
                        $scope.rating = savedRating;
                    });
            });
        }

    }]);