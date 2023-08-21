$(document).ready(function() {
    // <span class="date_txt"> 요소에서 선택한 날짜 가져오기
    let selectedDateText = $(".date_txt").text();
    let selectedDate = new Date(selectedDateText);
    let data = [];

    let labels = Array.from({
        length: 7
    }, (_, i) => {
        var currentDate = new Date(selectedDate);
        currentDate.setDate(selectedDate.getDate() - i);
        return currentDate.getDate();
    }).reverse();
    $.get({
        url: '/admin/statistics/visitor',
        data: {
            selectedDate: selectedDateText
        },
        success: function(response) {
            response = JSON.parse(JSON.stringify(response));
            if (response.success) {
                data = response.data;
            }
        },
        error: function(error) {
            console.error('요청 중 에러 발생:', error);
        }
    })
    // 그래프 생성
    const ctx = document.getElementById('myChart').getContext('2d');
    new Chart(ctx, {
        type: 'line', // 선 그래프로 설정
        data: {
            labels: labels,
            datasets: [{
                label: '방문자수',
                data: data,
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 2,
                fill: false
            }, ]
        },
        options: {
            responsive: true,
            plugins: {
                title: {
                    display: false,
                },
            },
            interaction: {
                intersect: false,
            },
            scales: {
                x: {
                    display: true,
                    title: {
                        display: true
                    }
                },
                y: {
                    display: true,
                    title: {
                        display: true,
                        text: 'Value'
                    },
                    suggestedMin: -10,
                    suggestedMax: 200
                }
            }
        },
    });
});
// 날짜 변경을 위한 함수
function changeDate(changeAmount) {
    // 현재 날짜 정보 가져오기
    let currentDate = new Date($(".date_txt").text());
    // 날짜 변경
    currentDate.setDate(currentDate.getDate() + changeAmount);
    // 변경된 날짜 업데이트
    const year = currentDate.getFullYear();
    const month = String(currentDate.getMonth() + 1).padStart(2, "0");
    const day = String(currentDate.getDate()).padStart(2, "0");
    const dayOfWeek = ["일", "월", "화", "수", "목", "금", "토"][currentDate.getDay()];

    // 화면에 날짜 업데이트
    $(".date_txt").text(year + "." + month + "." + day + " (" + dayOfWeek + ")");
}

// 이전 날짜로 가기 버튼 클릭 이벤트 처리
$(".date_button_on").click(function(e) {
    e.preventDefault();
    changeDate(-1); // -1일로 변경
});


// 다음 날짜로 가기 버튼 클릭 이벤트 처리
$(".date_button_next").click(function(e) {
    e.preventDefault();
    const currentDate = new Date($(".date_txt").text());
    const today = new Date();
    if (currentDate.getDate() < today.getDate()) {
        changeDate(1); // +1일로 변경
    }
    let selectedDateText = $(".date_txt").text();
    let selectedMonth = selectedDateText.split(".")[1];
    let graphTitle = selectedMonth + "월의 데이터 통계";
    $(".graph-title h4").text(graphTitle);
});

