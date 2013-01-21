var pentatonics;
var midinotes;
var midiroot;

s.boot;

pentatonics = [0, 3, 5, 7, 10, 12, 15, 17, 19, 21, 24];
pentatonics.postln;
midinotes = Array.new(16);
midiroot = 60;
// Test the notes in order:
//pentatonics.size.do({
//	arg i;
//	midinotes.add((midiroot + pentatonics.at(i)));
//});
15.do({
	arg i;
	midinotes.add(midiroot + pentatonics.at((pentatonics.size - 1).rand));
});

// Use an E root note instead of a C.
//midiroot = 64;
//pentatonics.size.do({
//	arg i;
//	midinotes.add((midiroot + pentatonics.at(i)));
//});

Pbind(\midinote, Pseq(midinotes, 1)).play;
