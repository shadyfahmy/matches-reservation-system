import { FormControl } from "@angular/forms";

export function customDateValidator(control: FormControl){
    if (!control.value)
        return null;
    let date = new Date(control.value).getTime();
    let minDate = Date.now()+ 3600*1000;
    let maxDate = Date.now()+ 3600*1000*24*365*2;
    console.log(date,minDate,maxDate)
    if(date >= minDate && date <= maxDate)
        return null;
    return {
        wrongDate:{
            "date":date
        }
    }
}