#Replace rsingh on both lines with the protocol you want to register and "/path/to/app" with the path to the application you want to run.
#In a terminal, type:
gconftool-2 -s /desktop/gnome/url-handlers/rsingh/command '/path/to/app %s' --type String
gconftool-2 -s /desktop/gnome/url-handlers/rsingh/enabled --type Boolean true
