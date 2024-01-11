// Post API 호출
function postApi(url, params, successCallback, errorCallback) {
  console.log("postApi >> [url : " + url + "], [params : " + JSON.stringify(params) + "]");

  $.ajax({
    type: 'POST',
    url: url,
    headers: {
      'Content-Type': 'application/json',
      'Accept': 'application/json'
    },
    data: JSON.stringify(params),
    success: function (data) {
      if (typeof successCallback === 'function') {
        successCallback(data);
      }
    },
    error: function (err) {
      if (typeof errorCallback === 'function') {
        errorCallback(err);
      }
    }
  });

}
