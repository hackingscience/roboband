s = Server.local;

(
SynthDef("pitchFollow1",{
    var in, amp, freq, hasFreq, out;
  //in = SinOsc.ar(523,0,0.5);//Mix.new(SoundIn.ar([0,1]));
	in = Mix.new(SoundIn.ar([0,1]));
	//amp = Amplitude.kr(in, 0.05, 0.05);
    # freq, hasFreq = Pitch.kr(in, ampThreshold: 0.1, median: 7);
	freq.scope;
    //freq = Lag.kr(freq.cpsmidi.round(1).midicps, 0.05);
	//hasFreq.poll(0.01);
	freq.poll(hasFreq, freq, 12);
	//freq.postln;
   /* out = Mix.new(VarSaw.ar(freq * [0.5,1,2], 0, LFNoise1.kr(0.3,0.1,0.1), amp));
    6.do({
        out = AllpassN.ar(out, 0.040, [0.040.rand,0.040.rand], 2)
    });
    Out.ar(0,out)
*/

}).play(s);
)
