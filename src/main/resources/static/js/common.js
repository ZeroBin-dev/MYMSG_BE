// Post API 호출
function postApi(url, params, successCallback, errorCallback) {
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

function multiApi(url, formData, successCallback, errorCallback) {

  $.ajax({
    type: 'POST',
    url: url,
    contentType: false, // contentType을 false로 설정하여 jQuery가 데이터를 문자열로 변환하지 않도록 합니다
    processData: false, // processData를 false로 설정하여 jQuery가 데이터를 처리하지 않도록 합니다
    data: formData,
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


// 모달 열기
function openModal() {
  document.querySelector('.modal').style.display = 'block';
}

// 모달 닫기
function closeModal() {
  document.querySelector('.modal').style.display = 'none';
}

function onApiError(err) {
  closeModal();
  alert(err.responseJSON.exception.errorMessage);
}

function onApiSuccess(data) {
  closeModal();
  alert(data.msg);
}

function goBack(){
  window.history.back();
}

