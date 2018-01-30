angular.module('Docstar.Admin.Documents').controller('Docstar.Admin.Documents.Details.Controller',
    ['$scope', '$resource', '$routeParams', '$uibModal', 'Session.Service', function ($scope, $resource, $routeParams, $modal, Session) {
        var Document = $resource('api/v1/admin/documents/:dID', {});
        var Rating = $resource('/api/v1/admin/documents/:dID/ratings');

        $scope.doc = Document.get({dID: $routeParams.dID});
        $scope.rating = Rating.query({dID: $routeParams.dID});
    }]
);