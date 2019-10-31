#Create a simulator object
set ns [new Simulator]
#Open the nam trace file
set nf [open reno.nam w]
$ns namtrace-all $nf
set nd [open reno.tr w]
$ns trace-all $nd
#Define a 'finish' procedure
proc finish {} {
global ns nf nd
$ns flush-trace
#Close the trace file
close $nf
close $nd
#Execute nam on the trace file
exec nam reno.nam &
exit 0
}
# Creating Nodes
set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
$ns duplex-link $n0 $n1 10Mb 10ms DropTail
$ns duplex-link $n1 $n2 2Mb 10ms DropTail
$ns duplex-link-op $n0 $n1 orient right-down
$ns duplex-link-op $n1 $n2 orient right-up
$ns queue-limit $n1 $n2 10
set tcp [new Agent/TCP/Newreno]
$ns attach-agent $n0 $tcp
set sink [new Agent/TCPSink]
$ns attach-agent $n2 $sink
$ns connect $tcp $sink
$tcp set fid_ 1
$tcp set window_ 8000
$tcp set packetSize_ 552
#Setup a FTP over TCP connection
set ftp [new Application/FTP]
$ftp attach-agent $tcp
$ftp set type_ FTP
#Create a UDP agent and attach it to node n0
set udp [new Agent/UDP]
$ns attach-agent $n0 $udp
# Create a CBR traffic source and attach it to udp0
set cbr [new Application/Traffic/CBR]
$cbr set type_ CBR
$cbr set packet_size_ 1000
$cbr set rate_ 0.01mb
$cbr set random_ false
$cbr attach-agent $udp
#Create a Null agent (a traffic sink) and attach it to node n1
set null [new Agent/Null]
$ns attach-agent $n2 $null
#Connect the traffic source with the traffic sink
$ns connect $udp $null
#Set Flow ID, Packet Size and Window Size
$udp set fid_ 2
$udp set window_ 8000
$udp set packetSize_ 552
#Start and stop the cbr and ftp
$ns at 0.1 "$cbr start"
$ns at 1.0 "$ftp start"
$ns at 4.5 "$ftp stop"
$ns at 5.0 "$cbr stop"
$ns at 5.0 "finish"
#Run the simulation
$ns run
