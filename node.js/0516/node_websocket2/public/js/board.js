// $(()=>{
//     var ctx = $('#cv').get(0).getContext('2d');
//     ctx.strokeStyle='#FF0000';
//     ctx.lineWidth= 5;
//     ctx.beginPath();

//     var drawing = false;
//     $('#cv').bind('mousedown', (e)=>{
//         drawing=true;
//         // 마우스 누른 자리로 이동
//         ctx.moveTo(e.pageX, e.pageY);
//     })

//     $('#cv').bind('mousemove', (e)=>{
//         if(drawing){
//             ctx.lineTo(e.pageX,e.pageY);
//             ctx.stroke();
//         }
//     })

//     $('#cv').bind('mouseup', (e)=>{
//         drawing=false;
//     })

//     // 선 그리기
//     // ctx.moveTo(100,100);
//     // ctx.lineTo(200,200);
//     // ctx.lineTo(150,200);
    
//     // 원 그리기
//     // ctx.arc(200,200,100,0,2*Math.PI);
    
//     // 안이 찬 사각형 그리기
//     //ctx.fillStyle='#FF0000';
//     //ctx.fillRect(0,0,200,200);
    
//     ctx.stroke();
// })
var ctx;
var socket;
$(()=>{
    // 소켓 생성
    socket = io.connect('http://localhost:8001',{
        path:'/socket.io',
        transport:['websocket']
    }); //+ window.location.host);
    // 소켓에서 linesend_toclient 이벤트가 발생했을 때 처리
    socket.on('linesend_toclient', (data)=>{draw.drawfromServer(data)});
    ctx = $('#cv').get(0).getContext('2d');

    //색상 선택 select 설정
    for(var key in color_map){
        $('#pen_color').append('<option value=' + color_map[key].value + '>' + color_map[key].name + '</option>');
    }

    //두께 선택 select 설정
    for(var i = 1 ; i < 16 ; i++){
        $('#pen_width').append('<option value=' + i + '>' + i + '</option>');
    }

    //select에 이벤트 연결
    $('select').bind('change', shape.change);
    
    // 기본 설정
    shape.setShape();

    $('#cv').bind('mousedown', draw.start);
    $('#cv').bind('mousemove', draw.move);
    $('#cv').bind('mouseup', draw.end);
    $('#clear').bind('click', draw.clear);
    
})

var shape = {
    // 기본 색상,두께 설정
    color : 'white',
    width : 3,

    //색 두께 변경 메서드
    change : function() {
        // jquery에서 select에서 선택된 항목을 찾을 때 사용
        // javascript의 경우는 select객체.options[select객체.selectedIndex].value
        var color = $('#pen_color option:selected').val();
        var width = $('#pen_width option:selected').val();
        shape.setShape(color, width);
    },

    // 모양 변경 메서드
    setShape : function(color, width) {
        if (color != null) this.color = color;
        if (width != null) this.width = width;
        ctx.strokeStyle = this.color;
        ctx.lineWidth = this.width;
        ctx.clearRect(703, 0, 860, 90);
        ctx.beginPath();
        ctx.moveTo(710, 55);
        ctx.lineTo(820, 55);
        ctx.stroke();
    }
}

var draw = {
    drawing : null,
    start : function(e){
        this.drawing=true;
        ctx.beginPath();
        ctx.moveTo(e.pageX, e.pageY);
        msg.line.send('start', e.pageX,e.pageY);
    },
    move : function(e){
        if(this.drawing){
            ctx.lineTo(e.pageX,e.pageY);
            ctx.stroke();
            msg.line.send('move',e.pageX,e.pageY);
        }
    },
    end : function(){
        this.drawing=false;
        msg.line.send('end');
    },
    clear:function(){
        ctx.clearRect(0,0,cv.width,cv.height);
        shape.setShape(); // clear하면 현재 붓의 모양까지 날아가니깐 다시 설정
        msg.line.send('clear');
    },
    drawfromServer: (data)=>{
        if(data.type=='start'){
            ctx.beginPath();
            ctx.moveTo(data.x, data.y);
            ctx.strokeStyle = data.color;
            ctx.lineWidth = data.width;
        }
        if(data.type=='move'){
            ctx.lineTo(data.x, data.y);
            ctx.stroke();
        }
        if(data.type=='clear'){
            ctx.clearRect(0,0,cv.width,cv.height);
            shape.setShape();
        }
    }
}

// 색상 선택 select에 추가할 내용
var color_map = [
    {'value':'white', 'name':'하얀색'},
    {'value':'red','name':'빨간색'},
    {'value':'orange','name':'주황색'},
    {'value':'yellow','name':'노란색'},
    {'value':'blue','name':'파랑색'}, 
    {'value':'black','name':'검은색'}
];


var msg = {
    line:{
        send:function(type,x,y){
            socket.emit('linesend',{
                'type': type,
                'x': x,
                'y': y,
                'color': shape.color,
                'width': shape.width
            });
        }
    }
}