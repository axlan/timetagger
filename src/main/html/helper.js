/**
 * Created by A on 4/26/2014.
 */

function getUnixTimeSec() {

    return Math.round(new Date().getTime() / 1000);
}

/**
 * @return {string}
 */
function pluralize(val)
{
    return (val==1)?'':'s';
}


/**
 * @return {string}
 */
function PrintElapsedTime(startTime)
{
    var elapsedSec=getUnixTimeSec()-startTime;
    return PrintTime(elapsedSec);
}

/**
 * @return {string}
 */
function PrintTime(elapsedSec)
{
    var sec=elapsedSec%60;

    var msg=sec+ ' second'+pluralize(sec);

    var minTotal=Math.floor(elapsedSec/60);
    if(minTotal>0)
    {
        var min=minTotal%60;
        msg=min+' minute'+pluralize(min)+' and '+msg;
        var hourTotal=Math.floor(minTotal/60);
        if(hourTotal>0)
        {
            var hour=hourTotal%24;
            msg=hour +' hour'+pluralize(hour)+' '+msg;
            var daysTotal=Math.floor(hourTotal/24);
            if(daysTotal>0)
            {
                msg=daysTotal + ' day'+pluralize(day)+' '+msg;
            }
        }
    }

    return msg;
}