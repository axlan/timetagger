/*
app.factory('stateFactory',function($http){
    var factory={};
    factory.getState=function(){
        return $http.get('/state.json');
    };
    return factory;
});
*/

app.controller("MainController", function($scope, $timeout,$http){


    $scope.PrintElapsedTime = PrintElapsedTime;
    $scope.PrintTime = PrintTime;
    $scope.inputRename=null;
    $scope.inputName=null;


    $scope.renaming=false;

    $scope.adding=false;


    function saveState()
    {
        $http.post('/updateState', $scope.state).success(function(result) {

        });
    }


    function init()
    {
        $http.get('/state.json').then(function(result) {
            $scope.state = result.data;



            $scope.selectedCategory = $scope.state.categories[0];
        });

    }

    init();

    $scope.startEvent = function() {
        if ($scope.selectedCategory != null&&$scope.selectedCategory.active==false) {

            $scope.selectedCategory.active=true;
            $scope.selectedCategory.startTime=getUnixTimeSec();
            console.log('startEvent '+$scope.selectedCategory.name+' '+getUnixTimeSec());
            saveState();
        }
    };



    $scope.deleteEvent = function() {

            if($scope.selectedCategory != null)
            {
                var i = $scope.state.categories.indexOf($scope.selectedCategory);

                $scope.state.totalTime-=$scope.selectedCategory.totalTime;
                $scope.state.categories.splice(i,1);
                saveState();

                if($scope.state.categories.length>0)
                {
                    $scope.selectedCategory = $scope.state.categories[0];
                }
                else
                {
                    $scope.selectedCategory = null;
                }

            }
    };


    $scope.renameEvent = function() {
        if($scope.renaming==true)
        {
            if($scope.selectedCategory != null&&$scope.inputRename!=null&&$scope.inputRename.length>0)
            {
                $scope.selectedCategory.name=$scope.inputRename;
                saveState();
            }
            $scope.renaming=false;
        }
        else
        {
            if($scope.selectedCategory != null)
            {
                $scope.inputRename=$scope.selectedCategory.name;
            }
            $scope.renaming=true;
        }
    };

    $scope.addEvent = function() {
        if($scope.adding==true)
        {
            if($scope.inputName!=null&&$scope.inputName.length>0)
            {
                $scope.state.categories.push(
                    {
                        id: $scope.nextId,
                        name: $scope.inputName,
                        totalTime: 0,
                        active: false,
                        startTime: 0
                    });
                $scope.state.nextId++;
                saveState();
            }
            $scope.adding=false;
        }
        else
        {
            $scope.inputName='';
            $scope.adding=true;
        }
    };

    $scope.stopEvent = function(cat) {
        if (cat.active==true) {

            cat.active=false;
            var stopTime=getUnixTimeSec();



            var eventTime=stopTime-cat.startTime;

            cat.totalTime+=eventTime;
            $scope.state.totalTime+=eventTime;

            console.log('stopEvent '+cat.name+' '+stopTime);
            saveState();
        }
    };

    // Function to replicate setInterval using $timeout service.
    $scope.intervalFunction = function(){
        $timeout(function() {
            $scope.intervalFunction();
        }, 1000)
    };

    // Kick off the interval
    $scope.intervalFunction();

});