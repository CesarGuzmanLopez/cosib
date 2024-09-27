"use strict";(self.webpackChunkapp=self.webpackChunkapp||[]).push([[8314],{8314:(D,m,c)=>{c.r(m),c.d(m,{ion_breadcrumb:()=>n,ion_breadcrumbs:()=>j});var o=c(4261),k=c(4920),l=c(333),u=c(1611),f=c(9483);const n=(()=>{let h=class{constructor(p){(0,o.r)(this,p),this.ionFocus=(0,o.d)(this,"ionFocus",7),this.ionBlur=(0,o.d)(this,"ionBlur",7),this.collapsedClick=(0,o.d)(this,"collapsedClick",7),this.inheritedAttributes={},this.onFocus=()=>{this.ionFocus.emit()},this.onBlur=()=>{this.ionBlur.emit()},this.collapsedIndicatorClick=()=>{this.collapsedClick.emit({ionShadowTarget:this.collapsedRef})},this.collapsed=!1,this.last=void 0,this.showCollapsedIndicator=void 0,this.color=void 0,this.active=!1,this.disabled=!1,this.download=void 0,this.href=void 0,this.rel=void 0,this.separator=void 0,this.target=void 0,this.routerDirection="forward",this.routerAnimation=void 0}componentWillLoad(){this.inheritedAttributes=(0,k.i)(this.el)}isClickable(){return void 0!==this.href}render(){const{color:p,active:d,collapsed:a,disabled:i,download:s,el:v,inheritedAttributes:e,last:b,routerAnimation:w,routerDirection:P,separator:S,showCollapsedIndicator:I,target:L}=this,A=this.isClickable(),E=void 0===this.href?"span":"a",M=i?void 0:this.href,z=(0,f.b)(this),R="span"===E?{}:{download:s,href:M,target:L},T=!b&&(a?!(!I||b):S);return(0,o.h)(o.f,{key:"dfe55ad57f23e5da5f2e2c51fea99964812472e3",onClick:_=>(0,l.o)(M,_,P,w),"aria-disabled":i?"true":null,class:(0,l.c)(p,{[z]:!0,"breadcrumb-active":d,"breadcrumb-collapsed":a,"breadcrumb-disabled":i,"in-breadcrumbs-color":(0,l.h)("ion-breadcrumbs[color]",v),"in-toolbar":(0,l.h)("ion-toolbar",this.el),"in-toolbar-color":(0,l.h)("ion-toolbar[color]",this.el),"ion-activatable":A,"ion-focusable":A})},(0,o.h)(E,Object.assign({key:"e361b1f0e7de6dd5c5dd4f6deae72c2c8210466d"},R,{class:"breadcrumb-native",part:"native",disabled:i,onFocus:this.onFocus,onBlur:this.onBlur},e),(0,o.h)("slot",{key:"9daeb45a8a28c89f3ad5751f71b8412197846371",name:"start"}),(0,o.h)("slot",{key:"4849e63cdffd6c712292745138b68730442c8b0d"}),(0,o.h)("slot",{key:"6edf9bac5aec06ccec2844efac2d9afa1d24cf57",name:"end"})),I&&(0,o.h)("button",{key:"de7f5faea75b44011b289d259265e2435a65874f",part:"collapsed-indicator","aria-label":"Show more breadcrumbs",onClick:()=>this.collapsedIndicatorClick(),ref:_=>this.collapsedRef=_,class:{"breadcrumbs-collapsed-indicator":!0}},(0,o.h)("ion-icon",{key:"957d8851af9c99dda263f34eff0b35a0fc212c32","aria-hidden":"true",icon:u.n,lazy:!1})),T&&(0,o.h)("span",{key:"97d646c37c4c41ad6b12c3a543d1146fde06fc9a",class:"breadcrumb-separator",part:"separator","aria-hidden":"true"},(0,o.h)("slot",{key:"0429f671a986f2d7be1b1b69fc879e80806d2916",name:"separator"},"ios"===z?(0,o.h)("ion-icon",{icon:u.m,lazy:!1,"flip-rtl":!0}):(0,o.h)("span",null,"/"))))}get el(){return(0,o.i)(this)}};return h.style={ios:":host{display:-ms-flexbox;display:flex;-ms-flex:0 0 auto;flex:0 0 auto;-ms-flex-align:center;align-items:center;color:var(--color);font-size:1rem;font-weight:400;line-height:1.5}.breadcrumb-native{font-family:inherit;font-size:inherit;font-style:inherit;font-weight:inherit;letter-spacing:inherit;text-decoration:inherit;text-indent:inherit;text-overflow:inherit;text-transform:inherit;text-align:inherit;white-space:inherit;color:inherit;padding-left:0;padding-right:0;padding-top:0;padding-bottom:0;margin-left:0;margin-right:0;margin-top:0;margin-bottom:0;display:-ms-flexbox;display:flex;-ms-flex-align:center;align-items:center;width:100%;outline:none;background:inherit}:host(.breadcrumb-disabled){cursor:default;opacity:0.5;pointer-events:none}:host(.breadcrumb-active){color:var(--color-active)}:host(.ion-focused){color:var(--color-focused)}:host(.ion-focused) .breadcrumb-native{background:var(--background-focused)}@media (any-hover: hover){:host(.ion-activatable:hover){color:var(--color-hover)}:host(.ion-activatable.in-breadcrumbs-color:hover),:host(.ion-activatable.ion-color:hover){color:var(--ion-color-shade)}}.breadcrumb-separator{display:-ms-inline-flexbox;display:inline-flex}:host(.breadcrumb-collapsed) .breadcrumb-native{display:none}:host(.in-breadcrumbs-color),:host(.in-breadcrumbs-color.breadcrumb-active){color:var(--ion-color-base)}:host(.in-breadcrumbs-color) .breadcrumb-separator{color:var(--ion-color-base)}:host(.ion-color){color:var(--ion-color-base)}:host(.in-toolbar-color),:host(.in-toolbar-color) .breadcrumb-separator{color:rgba(var(--ion-color-contrast-rgb), 0.8)}:host(.in-toolbar-color.breadcrumb-active){color:var(--ion-color-contrast)}.breadcrumbs-collapsed-indicator{padding-left:0;padding-right:0;padding-top:0;padding-bottom:0;-webkit-margin-start:14px;margin-inline-start:14px;-webkit-margin-end:14px;margin-inline-end:14px;margin-top:0;margin-bottom:0;display:-ms-flexbox;display:flex;-ms-flex:1 1 100%;flex:1 1 100%;-ms-flex-align:center;align-items:center;-ms-flex-pack:center;justify-content:center;width:32px;height:18px;border:0;outline:none;cursor:pointer;-webkit-appearance:none;-moz-appearance:none;appearance:none}.breadcrumbs-collapsed-indicator ion-icon{margin-top:1px;font-size:1.375rem}:host{--color:var(--ion-color-step-850, var(--ion-text-color-step-150, #2d4665));--color-active:var(--ion-text-color, #03060b);--color-hover:var(--ion-text-color, #03060b);--color-focused:var(--color-active);--background-focused:var(--ion-color-step-50, var(--ion-background-color-step-50, rgba(233, 237, 243, 0.7)));font-size:clamp(16px, 1rem, 22px)}:host(.breadcrumb-active){font-weight:600}.breadcrumb-native{border-radius:4px;-webkit-padding-start:12px;padding-inline-start:12px;-webkit-padding-end:12px;padding-inline-end:12px;padding-top:5px;padding-bottom:5px;border:1px solid transparent}:host(.ion-focused) .breadcrumb-native{border-radius:8px}:host(.in-breadcrumbs-color.ion-focused) .breadcrumb-native,:host(.ion-color.ion-focused) .breadcrumb-native{background:rgba(var(--ion-color-base-rgb), 0.1);color:var(--ion-color-base)}:host(.ion-focused) ::slotted(ion-icon),:host(.in-breadcrumbs-color.ion-focused) ::slotted(ion-icon),:host(.ion-color.ion-focused) ::slotted(ion-icon){color:var(--ion-color-step-750, var(--ion-text-color-step-250, #445b78))}.breadcrumb-separator{color:var(--ion-color-step-550, var(--ion-text-color-step-450, #73849a))}::slotted(ion-icon){color:var(--ion-color-step-400, var(--ion-text-color-step-600, #92a0b3));font-size:min(1.125rem, 21.6px)}::slotted(ion-icon[slot=start]){-webkit-margin-end:8px;margin-inline-end:8px}::slotted(ion-icon[slot=end]){-webkit-margin-start:8px;margin-inline-start:8px}:host(.breadcrumb-active) ::slotted(ion-icon){color:var(--ion-color-step-850, var(--ion-text-color-step-150, #242d39))}.breadcrumbs-collapsed-indicator{border-radius:4px;background:var(--ion-color-step-100, var(--ion-background-color-step-100, #e9edf3));color:var(--ion-color-step-550, var(--ion-text-color-step-450, #73849a))}.breadcrumbs-collapsed-indicator:hover{opacity:0.45}.breadcrumbs-collapsed-indicator:focus{background:var(--ion-color-step-150, var(--ion-background-color-step-150, #d9e0ea))}.breadcrumbs-collapsed-indicator ion-icon{font-size:min(1.375rem, 22px)}",md:":host{display:-ms-flexbox;display:flex;-ms-flex:0 0 auto;flex:0 0 auto;-ms-flex-align:center;align-items:center;color:var(--color);font-size:1rem;font-weight:400;line-height:1.5}.breadcrumb-native{font-family:inherit;font-size:inherit;font-style:inherit;font-weight:inherit;letter-spacing:inherit;text-decoration:inherit;text-indent:inherit;text-overflow:inherit;text-transform:inherit;text-align:inherit;white-space:inherit;color:inherit;padding-left:0;padding-right:0;padding-top:0;padding-bottom:0;margin-left:0;margin-right:0;margin-top:0;margin-bottom:0;display:-ms-flexbox;display:flex;-ms-flex-align:center;align-items:center;width:100%;outline:none;background:inherit}:host(.breadcrumb-disabled){cursor:default;opacity:0.5;pointer-events:none}:host(.breadcrumb-active){color:var(--color-active)}:host(.ion-focused){color:var(--color-focused)}:host(.ion-focused) .breadcrumb-native{background:var(--background-focused)}@media (any-hover: hover){:host(.ion-activatable:hover){color:var(--color-hover)}:host(.ion-activatable.in-breadcrumbs-color:hover),:host(.ion-activatable.ion-color:hover){color:var(--ion-color-shade)}}.breadcrumb-separator{display:-ms-inline-flexbox;display:inline-flex}:host(.breadcrumb-collapsed) .breadcrumb-native{display:none}:host(.in-breadcrumbs-color),:host(.in-breadcrumbs-color.breadcrumb-active){color:var(--ion-color-base)}:host(.in-breadcrumbs-color) .breadcrumb-separator{color:var(--ion-color-base)}:host(.ion-color){color:var(--ion-color-base)}:host(.in-toolbar-color),:host(.in-toolbar-color) .breadcrumb-separator{color:rgba(var(--ion-color-contrast-rgb), 0.8)}:host(.in-toolbar-color.breadcrumb-active){color:var(--ion-color-contrast)}.breadcrumbs-collapsed-indicator{padding-left:0;padding-right:0;padding-top:0;padding-bottom:0;-webkit-margin-start:14px;margin-inline-start:14px;-webkit-margin-end:14px;margin-inline-end:14px;margin-top:0;margin-bottom:0;display:-ms-flexbox;display:flex;-ms-flex:1 1 100%;flex:1 1 100%;-ms-flex-align:center;align-items:center;-ms-flex-pack:center;justify-content:center;width:32px;height:18px;border:0;outline:none;cursor:pointer;-webkit-appearance:none;-moz-appearance:none;appearance:none}.breadcrumbs-collapsed-indicator ion-icon{margin-top:1px;font-size:1.375rem}:host{--color:var(--ion-color-step-600, var(--ion-text-color-step-400, #677483));--color-active:var(--ion-text-color, #03060b);--color-hover:var(--ion-text-color, #03060b);--color-focused:var(--ion-color-step-800, var(--ion-text-color-step-200, #35404e));--background-focused:var(--ion-color-step-50, var(--ion-background-color-step-50, #fff))}:host(.breadcrumb-active){font-weight:500}.breadcrumb-native{-webkit-padding-start:12px;padding-inline-start:12px;-webkit-padding-end:12px;padding-inline-end:12px;padding-top:6px;padding-bottom:6px}.breadcrumb-separator{-webkit-margin-start:10px;margin-inline-start:10px;-webkit-margin-end:10px;margin-inline-end:10px;margin-top:-1px}:host(.ion-focused) .breadcrumb-native{border-radius:4px;-webkit-box-shadow:0px 1px 2px rgba(0, 0, 0, 0.2), 0px 2px 8px rgba(0, 0, 0, 0.12);box-shadow:0px 1px 2px rgba(0, 0, 0, 0.2), 0px 2px 8px rgba(0, 0, 0, 0.12)}.breadcrumb-separator{color:var(--ion-color-step-550, var(--ion-text-color-step-450, #73849a))}::slotted(ion-icon){color:var(--ion-color-step-550, var(--ion-text-color-step-450, #7d8894));font-size:1.125rem}::slotted(ion-icon[slot=start]){-webkit-margin-end:8px;margin-inline-end:8px}::slotted(ion-icon[slot=end]){-webkit-margin-start:8px;margin-inline-start:8px}:host(.breadcrumb-active) ::slotted(ion-icon){color:var(--ion-color-step-850, var(--ion-text-color-step-150, #222d3a))}.breadcrumbs-collapsed-indicator{border-radius:2px;background:var(--ion-color-step-100, var(--ion-background-color-step-100, #eef1f3));color:var(--ion-color-step-550, var(--ion-text-color-step-450, #73849a))}.breadcrumbs-collapsed-indicator:hover{opacity:0.7}.breadcrumbs-collapsed-indicator:focus{background:var(--ion-color-step-150, var(--ion-background-color-step-150, #dfe5e8))}"},h})(),j=(()=>{let h=class{constructor(p){(0,o.r)(this,p),this.ionCollapsedClick=(0,o.d)(this,"ionCollapsedClick",7),this.breadcrumbsInit=()=>{this.setBreadcrumbSeparator(),this.setMaxItems()},this.resetActiveBreadcrumb=()=>{const a=this.getBreadcrumbs().find(i=>i.active);a&&this.activeChanged&&(a.active=!1)},this.setMaxItems=()=>{const{itemsAfterCollapse:d,itemsBeforeCollapse:a,maxItems:i}=this,s=this.getBreadcrumbs();for(const e of s)e.showCollapsedIndicator=!1,e.collapsed=!1;void 0!==i&&s.length>i&&a+d<=i&&s.forEach((e,b)=>{b===a&&(e.showCollapsedIndicator=!0),b>=a&&b<s.length-d&&(e.collapsed=!0)})},this.setBreadcrumbSeparator=()=>{const{itemsAfterCollapse:d,itemsBeforeCollapse:a,maxItems:i}=this,s=this.getBreadcrumbs(),v=s.find(e=>e.active);for(const e of s){const b=void 0!==i&&0===d?e===s[a]:e===s[s.length-1];e.last=b,e.separator=void 0!==e.separator?e.separator:!b||void 0,!v&&b&&(e.active=!0,this.activeChanged=!0)}},this.getBreadcrumbs=()=>Array.from(this.el.querySelectorAll("ion-breadcrumb")),this.slotChanged=()=>{this.resetActiveBreadcrumb(),this.breadcrumbsInit()},this.collapsed=void 0,this.activeChanged=void 0,this.color=void 0,this.maxItems=void 0,this.itemsBeforeCollapse=1,this.itemsAfterCollapse=1}onCollapsedClick(p){const a=this.getBreadcrumbs().filter(i=>i.collapsed);this.ionCollapsedClick.emit(Object.assign(Object.assign({},p.detail),{collapsedBreadcrumbs:a}))}maxItemsChanged(){this.resetActiveBreadcrumb(),this.breadcrumbsInit()}componentWillLoad(){this.breadcrumbsInit()}render(){const{color:p,collapsed:d}=this,a=(0,f.b)(this);return(0,o.h)(o.f,{key:"18ffba1642f10cc2bc31440e84f23aa6f042e501",class:(0,l.c)(p,{[a]:!0,"in-toolbar":(0,l.h)("ion-toolbar",this.el),"in-toolbar-color":(0,l.h)("ion-toolbar[color]",this.el),"breadcrumbs-collapsed":d})},(0,o.h)("slot",{key:"3db6d31590e3047889ce554d57d155c8ea2e1455",onSlotchange:this.slotChanged}))}get el(){return(0,o.i)(this)}static get watchers(){return{maxItems:["maxItemsChanged"],itemsBeforeCollapse:["maxItemsChanged"],itemsAfterCollapse:["maxItemsChanged"]}}};return h.style={ios:":host{-moz-osx-font-smoothing:grayscale;-webkit-font-smoothing:antialiased;display:-ms-flexbox;display:flex;-ms-flex-wrap:wrap;flex-wrap:wrap;-ms-flex-align:center;align-items:center}:host(.in-toolbar-color),:host(.in-toolbar-color) .breadcrumbs-collapsed-indicator ion-icon{color:var(--ion-color-contrast)}:host(.in-toolbar-color) .breadcrumbs-collapsed-indicator{background:rgba(var(--ion-color-contrast-rgb), 0.11)}:host(.in-toolbar){-webkit-padding-start:20px;padding-inline-start:20px;-webkit-padding-end:20px;padding-inline-end:20px;padding-top:0;padding-bottom:0;-ms-flex-pack:center;justify-content:center}",md:":host{-moz-osx-font-smoothing:grayscale;-webkit-font-smoothing:antialiased;display:-ms-flexbox;display:flex;-ms-flex-wrap:wrap;flex-wrap:wrap;-ms-flex-align:center;align-items:center}:host(.in-toolbar-color),:host(.in-toolbar-color) .breadcrumbs-collapsed-indicator ion-icon{color:var(--ion-color-contrast)}:host(.in-toolbar-color) .breadcrumbs-collapsed-indicator{background:rgba(var(--ion-color-contrast-rgb), 0.11)}:host(.in-toolbar){-webkit-padding-start:8px;padding-inline-start:8px;-webkit-padding-end:8px;padding-inline-end:8px;padding-top:0;padding-bottom:0}"},h})()},333:(D,m,c)=>{c.d(m,{c:()=>l,g:()=>f,h:()=>k,o:()=>y});var o=c(467);const k=(t,r)=>null!==r.closest(t),l=(t,r)=>"string"==typeof t&&t.length>0?Object.assign({"ion-color":!0,[`ion-color-${t}`]:!0},r):r,f=t=>{const r={};return(t=>void 0!==t?(Array.isArray(t)?t:t.split(" ")).filter(n=>null!=n).map(n=>n.trim()).filter(n=>""!==n):[])(t).forEach(n=>r[n]=!0),r},B=/^[a-z][a-z0-9+\-.]*:/,y=function(){var t=(0,o.A)(function*(r,n,C,g){if(null!=r&&"#"!==r[0]&&!B.test(r)){const x=document.querySelector("ion-router");if(x)return null!=n&&n.preventDefault(),x.push(r,C,g)}return!1});return function(n,C,g,x){return t.apply(this,arguments)}}()}}]);