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
$(()=>{
    ctx = $('#cv').get(0).getContext('2d');
    // 기본 설정
    shape.setShape();

    $('#cv').bind('mousedown', draw.start);
    $('#cv').bind('mousemove', draw.move);
    $('#cv').bind('mouseup', draw.end);
    $('#clear').bind('click', draw.clear);
    
})

var shape = {
    color:'white',
    width:3,
    setShape : function(color,width){
        if(color != null){
            this.color = color;
        }
        if(width != null){
            this.width = width;
        }
        ctx.strokeStyle=this.color;
        ctx.lineWidth = this.width;
    }
}

var draw = {
    drawing : null,
    start : function(e){
        this.drawing=true;
        ctx.beginPath();
        ctx.moveTo(e.pageX, e.pageY);
    },
    move : function(e){
        if(this.drawing){
            ctx.lineTo(e.pageX,e.pageY);
            ctx.stroke();
        }
    },
    end : function(){
        this.drawing=false;
    },
    clear:function(){
        ctx.clearRect(0,0,cv.width,cv.height);
    }
}