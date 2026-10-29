
Set objShell = CreateObject("WScript.Shell")
Set objFSO = CreateObject("Scripting.FileSystemObject")

folder = objFSO.GetParentFolderName(WScript.ScriptFullName)
jarPath = folder & "\MQRGen-1.0-SNAPSHOT-all.jar"
logPath = folder & "\startup.log"

If Not objFSO.FileExists(jarPath) Then
    MsgBox "Application JAR not found:" & vbCrLf & jarPath, 16, "MQRGen"
    WScript.Quit
End If

command = "cmd.exe /c ""cd /d """ & folder & """ && java -jar """ & jarPath & """ > """ & logPath & """ 2>&1"""

objShell.Run command, 0, False
