(this.webpackJsonpmaterialui1a=this.webpackJsonpmaterialui1a||[]).push([[0],{36:function(e,t,a){e.exports=a(44)},44:function(e,t,a){"use strict";a.r(t);var n=a(0),r=a.n(n),i=a(16),c=a.n(i),l=a(20),u=a(7),o=a(32),m=a(10),s=a(8),p=a(11),b=a(12),h=a(13),j=a(60),O=a(62),d=a(63),f=a(27),y=a(64),E=function(e){function t(){return Object(m.a)(this,t),Object(p.a)(this,Object(b.a)(t).apply(this,arguments))}return Object(h.a)(t,e),Object(s.a)(t,[{key:"render",value:function(){return r.a.createElement(j.a,{position:"static"},r.a.createElement(O.a,null,r.a.createElement(d.a,{edge:"start",color:"inherit","aria-label":"menu"}),r.a.createElement(f.a,{variant:"h6"},"\u6211\u4f5b\u8fa3\uff01"),r.a.createElement(y.a,{color:"inherit"},"Login")))}}]),t}(n.Component),v=a(69),D=a(65),k=function(e){function t(){return Object(m.a)(this,t),Object(p.a)(this,Object(b.a)(t).apply(this,arguments))}return Object(h.a)(t,e),Object(s.a)(t,[{key:"render",value:function(){var e=this.props.mainData;return r.a.createElement(v.a,{value:0,variant:"fullWidth",centered:!0,"aria-label":"simple tabs example"},r.a.createElement(D.a,{key:"all",label:"\u5168\u90e8"}),e.map((function(e,t){return r.a.createElement(D.a,{key:t,label:e})})))}}]),t}(n.Component),g=a(68),w=a(45),L=a(66),C=a(70),x=a(67),B=function(e){function t(){return Object(m.a)(this,t),Object(p.a)(this,Object(b.a)(t).apply(this,arguments))}return Object(h.a)(t,e),Object(s.a)(t,[{key:"render",value:function(){var e=this.props.styles,t=this.props.newData;return r.a.createElement(w.a,{style:e.paper},t.map((function(e,t){return r.a.createElement("div",{key:"main"+t},r.a.createElement(f.a,{variant:"h6",key:"m"+t}," ",e[0]," "),r.a.createElement(L.a,{key:"L"+t},e[1].map((function(e,t){return r.a.createElement(C.a,{key:"LL"+t,button:!0},r.a.createElement(x.a,{key:"LLL"+t},e.title))}))))})))}}]),t}(n.Component),J=function(e){function t(){return Object(m.a)(this,t),Object(p.a)(this,Object(b.a)(t).apply(this,arguments))}return Object(h.a)(t,e),Object(s.a)(t,[{key:"render",value:function(){var e=this.props.styles;return r.a.createElement(w.a,{style:e.paper},"Right")}}]),t}(n.Component),A={paper:{marginTop:10,marginBottom:10,padding:30}},I=function(e){function t(){return Object(m.a)(this,t),Object(p.a)(this,Object(b.a)(t).apply(this,arguments))}return Object(h.a)(t,e),Object(s.a)(t,[{key:"render",value:function(){var e=this.props.newData;return(r.a.createElement(g.a,{container:!0,spacing:2},"   ",r.a.createElement(g.a,{item:!0,sm:!0}," ",r.a.createElement(B,{styles:A,newData:e}),"  "),r.a.createElement(g.a,{item:!0,sm:!0},r.a.createElement(J,{styles:A,newData:e}),"  ")))}}]),t}(n.Component),R=["\u9879\u76ee\u4e00","\u9879\u76ee\u4e8c","\u9879\u76ee\u4e09"],T=[{id:"1",title:"1-01",detail:"\u660e\u7ec61",mainData:"\u9879\u76ee\u4e00"},{id:"2",title:"1-02",detail:"\u660e\u7ec62",mainData:"\u9879\u76ee\u4e00"},{id:"3",title:"2-01",detail:"\u660e\u7ec63",mainData:"\u9879\u76ee\u4e8c"},{id:"4",title:"2-02",detail:"\u660e\u7ec64",mainData:"\u9879\u76ee\u4e8c"},{id:"5",title:"2-03",detail:"\u660e\u7ec65",mainData:"\u9879\u76ee\u4e8c"},{id:"6",title:"3-01",detail:"\u660e\u7ec66",mainData:"\u9879\u76ee\u4e09"}],W=function(e){function t(){var e,a;Object(m.a)(this,t);for(var n=arguments.length,r=new Array(n),i=0;i<n;i++)r[i]=arguments[i];return(a=Object(p.a)(this,(e=Object(b.a)(t)).call.apply(e,[this].concat(r)))).createData=function(){var e=R.reduce((function(e,t){return e=Object(o.a)({},e,Object(u.a)({},t,[]))}),{}),t=T.reduce((function(e,t){return e[t.mainData]=[].concat(Object(l.a)(e[t.mainData]),[t]),e}),e);return Object.entries(t)},a.handleDetail=function(e){},a}return Object(h.a)(t,e),Object(s.a)(t,[{key:"render",value:function(){console.log(this.createData());var e=this.createData();return r.a.createElement("div",null,r.a.createElement(E,null),r.a.createElement(I,{newData:e}),r.a.createElement(k,{mainData:R}))}}]),t}(n.Component);c.a.render(r.a.createElement(W,null),document.getElementById("root"))}},[[36,1,2]]]);
//# sourceMappingURL=main.726bd4fa.chunk.js.map